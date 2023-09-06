package com.ruoyi.system.service;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.system.domain.query.DeliveryQuery;
import com.ruoyi.system.domain.query.MachineEditQuery;
import com.ruoyi.system.domain.vo.*;

import java.util.List;

/**
 * 设备管理业务层接口
 *
 * @author lls
 */
public interface IMachineService {

    /**
     * 设备列表查询接口
     *
     * @param keyword     设备ID或者设备名 允许为null
     * @param stat        设备状态 1.已入库；2.已发货；3.已安装
     * @param page        页数
     * @param machineType 设备类型
     * @param overDate    设备有效期
     * @param name        备注名或微信名
     * @return 设备列表
     */
    PageInfo<MachineListVo> getMachineList(String keyword, String stat, String page, String machineType, String overDate, String name);

    /**
     * 设备详情接口
     *
     * @param id 设备id
     * @return 设备详情
     */
    MachineDetailVo detail(String id);

    /**
     * 设备操作日志
     *
     * @param page 页数
     * @param id   对应表中的user_machine_id
     * @return 日志集合
     */
    PageInfo<MachineActionLogVo> log(String page, String id);

    /**
     * 选择快递发货接口
     * 首先会根据id去yt_machine表中查询是否存在该设备，如果不存在则报不存在设备
     * 如果存在，更新这条记录的快递名称和单号
     *
     * @param deliveryQuery 包含了设备id、物流公司名称、物流单号
     * @return 响应数据
     */
    void delivery(DeliveryQuery deliveryQuery);

    /**
     * 在线发送指令接口
     * 1、检查该id是否在表中存在设备，如果不存在直接抛出异常
     * 2、如果code为pass
     * 2.1 检查当前设备的stat是否为0，如果不为0，则该设备已入库，无需操作
     * 2.2 如果stat为0，则更新其stat为1
     * 3、检查16进制命令是否正确
     * 4、操作redis
     *
     * @param commandVo 设备id和对应的16进制码
     * @param loginUser
     */
    void command(CommandVo commandVo, LoginUser loginUser);

    /**
     * 后台设备操作日志接口
     *
     * @param page 页数
     * @param id   设备id
     * @return 日志列表
     */
    PageInfo<MachineActionSystemLogVo> systemLog(String page, String id);

    /**
     * 设备删除接口
     *
     * @param id 设备id
     */
    void deleteMachine(String id);

    /**
     * 耗电统计
     *
     * @param id 设备id
     * @return
     */
    List<MachineElectricVo> electric(String id);

    OxygenLogVo getOxygenLog(String id);

    void editMachine(MachineEditQuery machineEditQuery);
}
