package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.system.domain.DayLog;
import com.ruoyi.system.domain.vo.Device4gNameVo;
import com.ruoyi.system.domain.vo.Device4gVo;
import com.ruoyi.system.service.DayLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.Device4gMapper;
import com.ruoyi.system.domain.Device4g;
import com.ruoyi.system.service.IDevice4gService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-04
 */
@Service
public class Device4gServiceImpl implements IDevice4gService
{
    @Autowired
    private Device4gMapper device4gMapper;
    @Autowired
    private DayLogService dayLogService;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Device4g selectDevice4gById(Integer id)
    {
        return device4gMapper.selectDevice4gById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param device4g 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Device4g> selectDevice4gList(Device4g device4g)
    {
        return device4gMapper.selectDevice4gList(device4g);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param device4g 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertDevice4g(Device4g device4g)
    {
        return device4gMapper.insertDevice4g(device4g);
    }

    /**
     * 修改【device4g 养殖场信息】,发布操作日志
     *
     * @param device4g 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateOrInsertDevice4g(Device4g device4g)
    {
        // 养殖场 默认值
        // device4g.setFishPond("一号养殖场");
        // 设备命名
        String machine_name = device4g.getFishPond() + device4gMapper.selectMaxIdByFishPond(device4g.getFishPond()) + "号" + device4g.getMachineType();
        device4g.setMachineName(machine_name);
        // 更新养殖场信息
        device4gMapper.updateDevice4g(device4g);
        // 取出设备全部信息
        device4g = device4gMapper.selectByMachineCode(device4g.getMachineCode());
        DayLog dayLog = new DayLog();
        BeanUtils.copyProperties(device4g,dayLog);
        dayLog.setMsgType(0);
        // 日志消息 拼接
        String msg =   device4g.getMachineCode() + "号设备的养殖场编号设置为" + device4g.getFishPond();
        dayLog.setMsg(msg);
        // 记录日志 将 某设备 的养殖场编号 设为 某养殖场
        return dayLogService.insertLog(dayLog);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDevice4gByIds(Integer[] ids)
    {
        return device4gMapper.deleteDevice4gByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDevice4gById(Integer id)
    {
        return device4gMapper.deleteDevice4gById(id);
    }

    @Override
    public Device4gVo countStatus(String fishPond) {
        Device4gVo device4gVo = device4gMapper.countStatus(fishPond);
        device4gVo.setFishPond(fishPond);
        return device4gVo;
    }

    @Override
    public List<Device4gNameVo> selectStatusList(Integer status,String fish_pond) {
        return device4gMapper.selectStatusList(status,fish_pond);
    }

    @Override
    public List<Device4gNameVo> selectAllStatusList(String fishPpond) {
        return device4gMapper.selectAllStatusList(fishPpond);
    }

    @Override
    public List<String> pondList() {
        List<String> res = new ArrayList<>();
        List<Device4g> device4gs = device4gMapper.pondList();
        for (Device4g device4g : device4gs) {
            res.add(device4g.getFishPond());
        }
        return res;
    }

    @Override
    public List<Device4g> selectNullFishPond() {
        return  device4gMapper.selectNullFishPond();
    }

    @Override
    public int updateDevice(Device4g device4g) {
        return device4gMapper.updateDevice4g(device4g);
    }
}
