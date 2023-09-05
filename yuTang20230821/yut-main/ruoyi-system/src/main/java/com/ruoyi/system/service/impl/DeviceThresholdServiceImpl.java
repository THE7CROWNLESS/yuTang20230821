package com.ruoyi.system.service.impl;

import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.Device4g;
import com.ruoyi.system.domain.YtMachineNew;
import com.ruoyi.system.mapper.DayLogMapper;
import com.ruoyi.system.mapper.Device4gMapper;
import com.ruoyi.system.mapper.YtMachineNewMapper;
import com.ruoyi.system.mqtt.MqttPushClient;
import com.ruoyi.system.utils.WarningUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DeviceThresholdMapper;
import com.ruoyi.system.domain.DeviceThreshold;
import com.ruoyi.system.service.IDeviceThresholdService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-02
 */
@Service
public class DeviceThresholdServiceImpl implements IDeviceThresholdService 
{
    @Autowired
    private DeviceThresholdMapper deviceThresholdMapper;
    @Autowired
    private WarningUtils warningUtils;
    @Autowired
    private DayLogMapper dayLogMapper;
    @Autowired
    private YtMachineNewMapper ytMachineNewMapper;
    @Autowired
    private Device4gMapper device4gMapper;
    @Autowired
    private MqttPushClient mqttPushClient;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public DeviceThreshold selectDeviceThresholdById(Integer id)
    {
        return deviceThresholdMapper.selectDeviceThresholdById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param deviceThreshold 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<DeviceThreshold> selectDeviceThresholdList(DeviceThreshold deviceThreshold)
    {
        return deviceThresholdMapper.selectDeviceThresholdList(deviceThreshold);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param deviceThreshold 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertDeviceThreshold(DeviceThreshold deviceThreshold)
    {
        return deviceThresholdMapper.insertDeviceThreshold(deviceThreshold);
    }

    /**
     * 修改【设备阈值】 发布mqtt消息? 记录日志
     *
     * @param deviceThreshold 【阈值】
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDeviceThreshold(DeviceThreshold deviceThreshold)
    {
        // 更新
        deviceThresholdMapper.updateDeviceThreshold(deviceThreshold);
        // 获取设备编码
        String machine_code = deviceThreshold.getMachineCode();
        // 取最新数据信息
        YtMachineNew ytMachineNew = ytMachineNewMapper.findMachineByMachineCode(machine_code);
        // 取设备信息
        Device4g device4g = device4gMapper.selectByMachineCode(machine_code);
        String machineName = device4g.getMachineName();
        // 日志信息 拼接
        String warningMsg = machineName + "阈值信息更改";
        // 日志信息 存表
        warningUtils.log(0,warningMsg,device4g,ytMachineNew);
//        // mqtt主题
//        String topic = "ecarbon/GET/" + device4g.getIMEI();
//        // mqtt信息 拼接
//        JSONObject message = new JSONObject();
//        message.putIfAbsent("IMEI",device4g.getIMEI());
//        message.putIfAbsent("")
//        warningUtils.mqttLogPublish(mqttPushClient,0,topic,warningMsg,message,device4g,ytMachineNew);

        // 更改后是否 在阈值范围
        warningUtils.thresholdSetWarning(machine_code);
        return 1;
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDeviceThresholdByIds(Integer[] ids)
    {
        return deviceThresholdMapper.deleteDeviceThresholdByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDeviceThresholdById(Integer id)
    {
        return deviceThresholdMapper.deleteDeviceThresholdById(id);
    }
}
