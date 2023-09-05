package com.ruoyi.system.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.DayLog;
import com.ruoyi.system.domain.Device4g;
import com.ruoyi.system.domain.DeviceThreshold;
import com.ruoyi.system.domain.YtMachineNew;
import com.ruoyi.system.mapper.DayLogMapper;
import com.ruoyi.system.mapper.Device4gMapper;
import com.ruoyi.system.mapper.DeviceThresholdMapper;
import com.ruoyi.system.mapper.YtMachineNewMapper;
import com.ruoyi.system.mqtt.MqttPushClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WarningUtils {
    @Autowired
    private DayLogMapper dayLogMapper;

    @Autowired
    private DeviceThresholdMapper deviceThresholdMapper;

    @Autowired
    private YtMachineNewMapper ytMachineNewMapper;

    @Autowired
    private Device4gMapper device4gMapper;


    public void mqttLogPublish(MqttPushClient mqttPushClient, Integer type,String topic,
                               String warningMsg,JSONObject message, Device4g device4g , YtMachineNew ytMachineNew){

        // 发布 mqtt
        mqttPushClient.publish(0,true,topic,message.toString());

        // 日志信息 存表
        log(type,warningMsg,device4g,ytMachineNew);
    }

    public void log( Integer type, String warningMsg, Device4g device4g , YtMachineNew ytMachineNew){

        // 日志信息 拼接
        DayLog dayLog = new DayLog();
        BeanUtils.copyProperties(device4g,dayLog);
        dayLog.setId(null);
        dayLog.setCreateTime(new Date());
        dayLog.setMsg(warningMsg);
        dayLog.setMsgType(type);
        try{
            dayLog.setPower(Float.parseFloat(ytMachineNew.getPower()));
        }catch (Exception e){
            dayLog.setPower((float) 0);
        }
        dayLogMapper.insert(dayLog);
    }

    public Boolean thresholdSetWarning(String machine_code){
        // 取最新数据信息
        YtMachineNew ytMachineNew = ytMachineNewMapper.findMachineByMachineCode(machine_code);
        // 取设备信息
        Device4g device4g = device4gMapper.selectByMachineCode(machine_code);
        String machineName = device4g.getMachineName();
        // 阈值
        DeviceThreshold deviceThreshold = deviceThresholdMapper.find_by_machine_code(machine_code);
        // 警告消息
        String warningMsg;

        // 判断 current voltage_max min  temp_max min  oxygen_max min 不符合就设置报警
        boolean flag = false; // 不报警
        if (ytMachineNew.getCurrent().compareTo(deviceThreshold.getCurrent()) > 0){
            // 电流大于阈值
            warningMsg = machineName + "电流大于阈值警告";
            this.log(1,warningMsg,device4g,ytMachineNew);
            flag = true;
        }
        if (ytMachineNew.getVoltage().compareTo(deviceThreshold.getVoltageMax()) > 0){
            warningMsg = machineName + "电压大于阈值警告";
            this.log(1,warningMsg,device4g,ytMachineNew);
            flag = true;
        }
        if (ytMachineNew.getVoltage().compareTo(deviceThreshold.getVoltageMin()) < 0){
            warningMsg = machineName + "电压小于阈值警告";
            this.log(1,warningMsg,device4g,ytMachineNew);
            flag = true;
        }
        if (ytMachineNew.getTemperature().compareTo(deviceThreshold.getTemperatureMax()) > 0){
            warningMsg = machineName + "温度大于阈值警告";
            this.log(1,warningMsg,device4g,ytMachineNew);
            flag = true;
        }
        if (ytMachineNew.getTemperature().compareTo(deviceThreshold.getTemperatureMin()) < 0){
            warningMsg = machineName + "温度小于阈值警告";
            this.log(1,warningMsg,device4g,ytMachineNew);
            flag = true;
        }
        if (ytMachineNew.getOxygen().compareTo(deviceThreshold.getOxygenMax()) > 0){
            warningMsg = machineName + "溶氧量大于阈值警告";
            this.log(1,warningMsg,device4g,ytMachineNew);
            flag = true;
        }
        if (ytMachineNew.getOxygen().compareTo(deviceThreshold.getOxygenMin()) < 0){
            warningMsg = machineName + "溶氧量小于阈值警告";
            this.log(1,warningMsg,device4g,ytMachineNew);
            flag = true;
        }
        return flag;
    }

    public Boolean thresholdWarning(String machine_code) {
        // 取最新数据信息
        YtMachineNew ytMachineNew = ytMachineNewMapper.findMachineByMachineCode(machine_code);
        // 阈值
        DeviceThreshold deviceThreshold = deviceThresholdMapper.find_by_machine_code(machine_code);

        // 判断 current voltage_max min  temp_max min  oxygen_max min 不符合就设置报警
        boolean flag = false; // 不报警
        if (ytMachineNew.getCurrent().compareTo(deviceThreshold.getCurrent()) > 0) {
            // 电流大于阈值
            flag = true;
        }
        if (ytMachineNew.getVoltage().compareTo(deviceThreshold.getVoltageMax()) > 0) {
            flag = true;
        }
        if (ytMachineNew.getVoltage().compareTo(deviceThreshold.getVoltageMin()) < 0) {
            flag = true;
        }
        if (ytMachineNew.getTemperature().compareTo(deviceThreshold.getTemperatureMax()) > 0) {
            flag = true;
        }
        if (ytMachineNew.getTemperature().compareTo(deviceThreshold.getTemperatureMin()) < 0) {
            flag = true;
        }
        if (ytMachineNew.getOxygen().compareTo(deviceThreshold.getOxygenMax()) > 0) {
            flag = true;
        }
        if (ytMachineNew.getOxygen().compareTo(deviceThreshold.getOxygenMin()) < 0) {
            flag = true;
        }
        return flag;
    }

    public String parseStatus(int constant){
        if (constant == 0){
            return "正常";
        } else if (constant == 1) {
            return  "关机";
        }else {
            return "故障";
        }
    }
    public String parseType(Integer constant){
        if (constant == null){
            return "为查询到状态";
        } else if (constant == 1) {
            return  "缺A相";
        }else if (constant == 2){
            return "缺B相";
        }else  {
            return "缺C相";
        }
    }
}
