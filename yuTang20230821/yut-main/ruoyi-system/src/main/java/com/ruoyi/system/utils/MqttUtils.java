package com.ruoyi.system.utils;

import com.alibaba.druid.sql.dialect.h2.visitor.H2ASTVisitor;
import com.alibaba.fastjson2.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MqttUtils {
    public static JSONObject parseSensorData(String sensorDataStr) {
        JSONObject data = new JSONObject();
        // sensorDataStr = "~868739054520592206004C0005272601591B6B06C8FF09010002000300040005000600070009000C000D000E0010001100000E`";

        // System.out.println("01: 字符串总长度[103个]："+ sensorDataStr.length());
        // System.arraycopy(sm4KeyEnBytes, 0, sm2EnX, 32, 32);

        String IMEI = sensorDataStr.substring(1,16);

        // System.out.println("01-16:IMEI[length]: "+IMEI.length()+": "+IMEI);

        String atmosphericStr = sensorDataStr.substring(27,31);

        // System.out.println("27-31:atmos[length]: "+atmosphericStr.length()+": "+atmosphericStr);

        String temperatureStr = sensorDataStr.substring(51,55);
        String dissolvedOxygenStr = sensorDataStr.substring(55,59);
        String PH_Str = sensorDataStr.substring(63,67);

        // System.out.println(temperatureStr);

        double temperature = 0.1 * Integer.parseInt(temperatureStr,16);
        double atmosphere = 0.01 * Integer.parseInt(atmosphericStr,16);
        double dissolvedOxygen = 0.01 * Integer.parseInt(dissolvedOxygenStr,16);
        double PH = 0.01 * Integer.parseInt(PH_Str,16);
        data.put("temperature", String.valueOf(temperature));
        data.put("atmosphere", String.valueOf(atmosphere));
        data.put("oxygen", String.valueOf(dissolvedOxygen));
        data.put("ph", String.valueOf(PH));
//        System.out.println("temperature: "+temperatureStr +" 值："+temperature);
//        System.out.println("dissolvedOxygen: "+dissolvedOxygenStr +" 值："+dissolvedOxygen);
//        System.out.println("PH: "+PH_Str +" 值："+PH);
        return data;
    }
}
