package com.ruoyi.system.utils;

import com.alibaba.fastjson2.JSONObject;

public class MqttUtils {
    public static JSONObject parseSensorData(String sensorDataStr) {
        JSONObject data = new JSONObject();
        // sensorDataStr = "~868739054520592206004C0005272601591B6B06C8FF09010002000300040005000600070009000C000D000E0010001100000E`";
        //                  ~869298054643580004001C0001010301130203021303030401000C`

        String IMEI = sensorDataStr.substring(1,16);

        // 判断 数据信息 or 基本信息
        String sensorType = sensorDataStr.substring(17,19);
        // 04 则不处理
        if (Integer.parseInt(sensorType,16) == 4) {
            return null;
        }
        //String dataLength = sensorDataStr.substring(19,23);
        //String atmosphericStr = sensorDataStr.substring(27,31);

        String temperatureStr = sensorDataStr.substring(27,31);
        String dissolvedOxygenStr = sensorDataStr.substring(31,35);
        String PH_Str = sensorDataStr.substring(39,43);

        double temperature = 0.1 * Integer.parseInt(temperatureStr,16);
        //double atmosphere = 0.01 * Integer.parseInt(atmosphericStr,16);
        double dissolvedOxygen = 0.01 * Integer.parseInt(dissolvedOxygenStr,16);
        double PH = 0.01 * Integer.parseInt(PH_Str,16);
        data.put("temperature", String.valueOf(temperature));
        //data.put("atmosphere", String.valueOf(atmosphere));
        data.put("oxygen", String.valueOf(dissolvedOxygen));
        data.put("ph", String.valueOf(PH));
//        System.out.println("temperature: "+temperatureStr +" 值："+temperature);
//        System.out.println("dissolvedOxygen: "+dissolvedOxygenStr +" 值："+dissolvedOxygen);
//        System.out.println("PH: "+PH_Str +" 值："+PH);
        return data;
    }

}
