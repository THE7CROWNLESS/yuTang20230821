package com.ruoyi.system.utils;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.DayLog;
import com.ruoyi.system.domain.Device4g;
import com.ruoyi.system.domain.YtMachineNew;
import com.ruoyi.system.mapper.DayLogMapper;
import com.ruoyi.system.mapper.Device4gMapper;
import com.ruoyi.system.mapper.YtMachineNewMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class MqttUtils {

    @Autowired
    private  YtMachineNewMapper ytMachineNewMapper;
    @Autowired
    private Device4gMapper device4gMapper;
    @Autowired
    private  DayLogMapper dayLogMapper;
    @Autowired
    private  WarningUtils warningUtils;

    public static JSONObject parseSensorData(String sensorDataStr) {
        JSONObject data = new JSONObject();
        // sensorDataStr = "~868739054520592206004C0005272601591B6B06C8FF09010002000300040005000600070009000C000D000E0010001100000E`";
        //                  ~869298054643580004001C0001010301130203021303030401000C`

        String IMEI = sensorDataStr.substring(1, 16);

        // 判断 数据信息 or 基本信息
        String sensorType = sensorDataStr.substring(17, 19);
        // 04 则不处理
        if (Integer.parseInt(sensorType, 16) == 4) {
            return null;
        }
        //String dataLength = sensorDataStr.substring(19,23);
        //String atmosphericStr = sensorDataStr.substring(27,31);

        String temperatureStr = sensorDataStr.substring(27, 31);
        String dissolvedOxygenStr = sensorDataStr.substring(31, 35);
        String PH_Str = sensorDataStr.substring(39, 43);

        double temperature = 0.1 * Integer.parseInt(temperatureStr, 16);
        //double atmosphere = 0.01 * Integer.parseInt(atmosphericStr,16);
        double dissolvedOxygen = 0.01 * Integer.parseInt(dissolvedOxygenStr, 16);
        double PH = 0.01 * Integer.parseInt(PH_Str, 16);
        data.put("temperature", String.valueOf(temperature));
        //data.put("atmosphere", String.valueOf(atmosphere));
        data.put("oxygen", String.valueOf(dissolvedOxygen));
        data.put("ph", String.valueOf(PH));
//        System.out.println("temperature: "+temperatureStr +" 值："+temperature);
//        System.out.println("dissolvedOxygen: "+dissolvedOxygenStr +" 值："+dissolvedOxygen);
//        System.out.println("PH: "+PH_Str +" 值："+PH);
        return data;
    }

    public  void dealMode(YtMachineNew newData, Device4g device4g) {
        String machineCode = newData.getMachineCode();
        YtMachineNew oldData = ytMachineNewMapper.findMachineByMachineCode(machineCode);
        // 新设备没有之前数据
        if (oldData == null) {
            oldData = newData;
        }

        String machineType = device4g.getMachineType();
        if (!Objects.equals(newData.getX1Model(), oldData.getX1Model())) {
            String msg = "编号" + machineCode + machineType + "第1号电机状态从现场设置由" +
                    parseMode(oldData.getX1Model()) + "转为" + parseMode(newData.getX1Model());
                    warningUtils.log(0, msg, device4g, newData);
        }
        if (!Objects.equals(newData.getX2Model(), oldData.getX2Model())) {
            String msg = "编号" + machineCode + machineType + "第2号电机状态从现场设置由" +
                    parseMode(oldData.getX2Model()) + "转为" + parseMode(newData.getX2Model());
            warningUtils.log(0, msg, device4g, newData);
        }
        if (!Objects.equals(newData.getX3Model(), oldData.getX3Model())) {
            String msg = "编号" + machineCode + machineType + "第3号电机状态从现场设置由" +
                    parseMode(oldData.getX3Model()) + "转为" + parseMode(newData.getX3Model());
            warningUtils.log(0, msg, device4g, newData);
        }
        if (!Objects.equals(newData.getX4Model(), oldData.getX4Model())) {
            String msg = "编号" + machineCode + machineType + "第4号电机状态从现场设置由" +
                    parseMode(oldData.getX4Model()) + "转为" + parseMode(newData.getX4Model());
            warningUtils.log(0, msg, device4g, newData);
        }
    }

    public static String parseMode(Integer mode){
        if (mode == 0) {
            return "手动控制";
        }else {
            return "自动控制";
        }
    }

    public  void dealPhase(YtMachineNew ytMachineNew, Device4g device4g){
        if (ytMachineNew.getPhase() != 0) {
            DayLog dayLog = new DayLog();
            // Device4g device4g = device4gMapper.selectByMachineCode(machine_coe);
            BeanUtils.copyProperties(device4g, dayLog);
            dayLog.setId(null);
            dayLog.setCreateTime(new Date());
            String machineName = device4g.getMachineName();
            String warningMsg = machineName + warningUtils.parseType(ytMachineNew.getPhase());
            dayLog.setMsg(warningMsg);
            dayLog.setMsgType(1);
            dayLog.setPower(Float.valueOf(ytMachineNew.getPower()));
            // logger.info((dayLog.toString()));
            dayLogMapper.insert(dayLog);
        }
    }

}
