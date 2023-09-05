package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.Device4g;
import com.ruoyi.system.mapper.Device4gMapper;
import com.ruoyi.system.mqtt.MqttPushClient;
import com.ruoyi.system.utils.WarningUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.YtMachineNewMapper;
import com.ruoyi.system.domain.YtMachineNew;
import com.ruoyi.system.service.IYtMachineNewService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-02
 */
@Service
public class YtMachineNewServiceImpl implements IYtMachineNewService 
{
    @Autowired
    private YtMachineNewMapper ytMachineNewMapper;

    @Autowired
    private WarningUtils warningUtils;
    @Autowired
    private Device4gMapper device4gMapper;

    /**
     * 查询设备
     * 
     * @param id 设备主键
     * @return 设备
     */
    @Override
    public YtMachineNew selectYtMachineNewById(Integer id)
    {
        return ytMachineNewMapper.selectYtMachineNewById(id);
    }

    /**
     * 查询设备列表
     * 
     * @param ytMachineNew 设备
     * @return 设备
     */
    @Override
    public List<YtMachineNew> selectYtMachineNewList(YtMachineNew ytMachineNew)
    {
        return ytMachineNewMapper.selectYtMachineNewList(ytMachineNew);
    }

    /**
     * 新增设备
     * 
     * @param ytMachineNew 设备
     * @return 结果
     */
    @Override
    public int insertYtMachineNew(YtMachineNew ytMachineNew)
    {
        return ytMachineNewMapper.insertYtMachineNew(ytMachineNew);
    }

    /**
     * 修改设备
     * 
     * @param ytMachineNew 设备
     * @return 结果
     */
    @Override
    public int updateYtMachineNew(YtMachineNew ytMachineNew)
    {
        return ytMachineNewMapper.updateYtMachineNew(ytMachineNew);
    }

    /**
     * 批量删除设备
     * 
     * @param ids 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteYtMachineNewByIds(Integer[] ids)
    {
        return ytMachineNewMapper.deleteYtMachineNewByIds(ids);
    }

    /**
     * 删除设备信息
     * 
     * @param id 设备主键
     * @return 结果
     */
    @Override
    public int deleteYtMachineNewById(Integer id)
    {
        return ytMachineNewMapper.deleteYtMachineNewById(id);
    }

    //设置 设备 的开关机状态，发布mqtt消息，记录日志
    @Override
    @Transactional
    public String updateMqttAerator(YtMachineNew machineData,String num, Integer old, Integer change) {
        // 改变 插入时间
        machineData.setConnectTime(new Date());
        // 插入 状态改变 消息
        ytMachineNewMapper.insertAeratorChange(machineData);
        // 设备编码
        String machineCode = machineData.getMachineCode();
        // 取设备信息
        Device4g device4g = device4gMapper.selectByMachineCode(machineCode);
        // 拼接日志消息
        String machineName = device4g.getMachineName();
        String warningMsg = machineName + "电机" + num +  "状态由" + old.toString() + "强制转变为" + change.toString();

        // mqtt主题
        String topic = "ecarbon/GET/" + device4g.getIMEI();
        // mqtt信息 拼接
        JSONObject message = new JSONObject();
        String key = "control." + num;
        message.putIfAbsent(key, change);
        // 发布 mqtt
        warningUtils.mqttLogPublish(new MqttPushClient(),0,topic,warningMsg,message,device4g,machineData);

        return null;
    }

}
