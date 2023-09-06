package com.ruoyi.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.entity.YtAdminUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.system.domain.Machine;
import com.ruoyi.system.domain.MachineActionSystemLog;
import com.ruoyi.system.domain.UserMachine;
import com.ruoyi.system.domain.query.DeliveryQuery;
import com.ruoyi.system.domain.query.MachineEditQuery;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.mapper.MachineMapper;
import com.ruoyi.system.service.IMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 设备管理业务层实现类
 *
 * @author lls
 */
@Service
public class MachineServiceImpl implements IMachineService {

    @Resource(name = "machineMapper")
    private MachineMapper machineMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public PageInfo<MachineListVo> getMachineList(String keyword, String stat, String page, String machineType, String overDate, String name) {

        PageHelper.startPage(Integer.parseInt(page), 10);
        List<MachineListVo> list = machineMapper.getMachineList(keyword, stat, machineType, overDate, name);
        PageInfo<MachineListVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public MachineDetailVo detail(String id) {
        MachineDetailVo machineDetailVo = machineMapper.detail(id);
        return machineDetailVo;
    }

    @Override
    public PageInfo<MachineActionLogVo> log(String page, String id) {
        PageHelper.startPage(Integer.parseInt(page), 10);
        List<MachineActionLogVo> list = machineMapper.log(id);
        PageInfo<MachineActionLogVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void delivery(DeliveryQuery deliveryQuery) {
        int count = machineMapper.getMachineCountById(deliveryQuery.getId());
        if (count == 0) {
            // 设备表中没有这个设备
            throw new ServiceException("设备不存在！", 10001);
        } else {
            // 设备存在，更新该记录
            int row = machineMapper.updateMachineExpressById(deliveryQuery);
            if (row == 0) {
                throw new ServiceException("更新设备物流信息失败！", 10002);
            }
        }
    }

    @Override
    public void command(CommandVo commandVo, LoginUser loginUser) {
        // 1、查看当前设备id是否在表中存在
        String id = commandVo.getId();
        String code = commandVo.getCode();
        Machine machine = machineMapper.getMachineById(Integer.parseInt(id));
        if (machine == null) {
            // 设备表中没有这个设备
            throw new ServiceException("设备不存在！", 10001);
        }
        // 如果命令code为pass，则直接更新stat状态
        if ("pass".equals(code)) {
            // 判断该设备目前是否入库，如果已经入库，则无需操作，stat为0表示未入库
            if (machine.getStat() != 0) {
                // 当前设备已入库
                throw new ServiceException("该设备已入库无需操作", 10002);
            }
            // 更新设备状态为1，表示让该设备入库
            int count = machineMapper.updateMachineStatById(Integer.parseInt(id), 1);
            if (count == 0) {
                throw new ServiceException("设备入库失败！", 10002);
            } else {
                return;
            }
        }
        // 命令不是pass，则需要检验是否为正确的16进制
        if (!code.matches("[0-9a-fA-F]+")) {
            // 非16进制，抛出异常
            throw new ServiceException("非法命令", 10099);
        }
        // 命令格式正确
        // 检验设备是否设置了类型
        if (null == machine.getMachineType()) {
            throw new ServiceException("请先设置设备类型", 10001);
        }
        System.out.println(loginUser);
        YtAdminUser user = loginUser.getUser();
        String msg = "system|" + machine.getIMEI() + "|" + user.getId() + "|" + code;

        // 发送指令
        Long send = redisTemplate.opsForList().leftPush("ecb_console_notice", msg);

        // 保存设备操作系统日志到数据库
        MachineActionSystemLog machineActionSystemLog = new MachineActionSystemLog();
        machineActionSystemLog.setMachineCode(machine.getMachineCode())
                .setMachineId(machine.getId())
                .setMachineName(machine.getMachineName())
                .setIMEI(machine.getIMEI())
                .setAdminId(user.getId().intValue())
                .setAdminName(user.getUsername())
                .setIp(IpUtils.getIpAddr())
                .setSend(send.intValue())
                .setSendMsg(msg)
                .setCreateTime((int) (System.currentTimeMillis() / 1000))
                .setUpdateTime((int) (System.currentTimeMillis() / 1000));
        machineMapper.saveMachineActionSystemLog(machineActionSystemLog);
        if (send == 0) {
            throw new ServiceException("发送指令失败!", 10098);
        }

    }

    @Override
    public PageInfo<MachineActionSystemLogVo> systemLog(String page, String id) {

        PageHelper.startPage(Integer.parseInt(page), 10);
        List<MachineActionSystemLogVo> list = machineMapper.systemLog(id);
        PageInfo<MachineActionSystemLogVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional
    public void deleteMachine(String id) {
        // 查看设备是否存在
        Machine machine = machineMapper.getMachineById(Integer.parseInt(id));
        if (machine == null) {
            throw new ServiceException("设备不存在！", 10001);
        }
        if (machine.getIsDel() == 1) {
            throw new ServiceException("重复删除操作！", 10003);
        }
        try {
            // 设置设备删除
            machineMapper.updateMachineDelById(id, 1);
            // 根据id作为machine_id从yt_user_machine表中查出记录，设置其被删除
            UserMachine userMachine = machineMapper.getUserMachineByMachineId(id);
            Integer userMachineId = userMachine.getId();
            machineMapper.updateUserMachineDelById(userMachineId, 1);
            // 将管理员和塘主的组记录清空
            machineMapper.deletePondGroupMachineByUserMachineId(userMachineId);
            // 将放置规则一同删除
            machineMapper.deletePondMachineRuleByUserMachineId(userMachineId);
            // 将对应任务删除
            machineMapper.deleteTaskSettingByUserMachineId(userMachineId);
            // 移除绑定
            machineMapper.deleteMachineOxygenMiddleByOxUserMachineId(userMachineId);
        } catch (Exception e) {
            throw new ServiceException("删除设备失败！", 10004);
        }
    }

    @Override
    public List<MachineElectricVo> electric(String id) {
        return machineMapper.getMachineElectric(id);
    }

    @Override
    public OxygenLogVo getOxygenLog(String id) {
        // 开始时间设置为当前日期-6天
        LocalDate startDate = LocalDate.now().minusDays(6);
        // 开始生成日期list
        List<String> dates = new ArrayList<>();
        dates.add(startDate.toString());
        for (int i = 0; i < 5; i++) {
            startDate = startDate.plusDays(1);
            dates.add(startDate.toString());
        }
        // 生成小时数组
        List<String> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                hours.add("0" + i);
            } else {
                hours.add(i + "");
            }
        }
        // 根据id查询出对应设备
        Machine machine = machineMapper.getMachineById(Integer.parseInt(id));
        if (machine == null) {
            throw new ServiceException("该设备不存在！", 10001);
        }
        if (machine.getMachineType() != 4) {
            throw new ServiceException("该设备不是溶氧仪", 10004);
        }
        // 查询出对应的log日志


        return null;
    }

    @Override
    @Transactional
    public void editMachine(MachineEditQuery machineEditQuery) {
        String id = machineEditQuery.getId();
        String machineType = machineEditQuery.getMachine_type();
//        String endBindDate = machineEditQuery.getEnd_bind_date();
//        String overDate = machineEditQuery.getOver_date();
        if (id == null || "".equals(id)) {
            throw new ServiceException("没有选择设备类型不能提交！",10001);
        }
        // 根据id更新该四个字段
        machineMapper.updateMachineEditById(machineEditQuery);
        machineMapper.updateUserMachineType(id,machineType);

        Machine machine = machineMapper.getMachineById(Integer.parseInt(id));
//        redisTemplate.opsFor

    }

}
