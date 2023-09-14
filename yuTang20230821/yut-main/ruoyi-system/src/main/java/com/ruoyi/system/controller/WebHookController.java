package com.ruoyi.system.controller;

import com.ruoyi.system.utils.MqttUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mqtt")
public class WebHookController {
    @Autowired
    private MqttUtils mqttUtils;
    private static  final Logger logger = LoggerFactory.getLogger(WebHookController.class);
    private Map<String,Boolean> clientStatus = new HashMap<>();
    @PostMapping("/webhook")
    public void hook(@RequestBody Map<String,Object> params){
        // logger.info("emqx触发webhook，请求体数据={}",params);

        String action = (String) params.get("action");
        String clientId = (String) params.get("clientid");
        if (action.equals("client_connected")){
            // logger.info("客户端{}接入本系统",clientId);
            // 非第一次接入，需在此更新status
            mqttUtils.dealOn(clientId);
            clientStatus.put(clientId,true);
        }
        if (action.equals("client_disconnected")){
            // logger.info("客户端{}下线",clientId);
            clientStatus.put(clientId,false);
            // 处理离线，存设备表，发警告消息
            mqttUtils.dealOff(clientId);
        }
        // 日志
    }

    @GetMapping("/allStatus")
    public Map<String,Boolean> getSatus(){
        return  this.clientStatus;
    }
}
