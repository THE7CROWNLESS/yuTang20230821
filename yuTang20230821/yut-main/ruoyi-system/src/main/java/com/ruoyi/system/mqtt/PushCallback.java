package com.ruoyi.system.mqtt;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.DayLog;
import com.ruoyi.system.domain.Device4g;
import com.ruoyi.system.domain.YtMachineNew;
import com.ruoyi.system.mapper.DayLogMapper;
import com.ruoyi.system.mapper.Device4gMapper;
import com.ruoyi.system.mapper.YtMachineNewMapper;
import com.ruoyi.system.utils.MqttUtils;
import com.ruoyi.system.utils.WarningUtils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PushCallback implements MqttCallback {
    private static final Logger logger = LoggerFactory.getLogger(MqttPushClient.class);
    @Autowired
    private WarningUtils warningUtils;
    @Autowired
    private DayLogMapper dayLogMapper;
    @Autowired
    private YtMachineNewMapper ytMachineNewMapper;

    @Autowired
    private Device4gMapper device4gMapper;

    @Autowired
    private MqttConfig mqttConfig;
    private static MqttClient client;
    private static String _topic;
    private static String _qos;
    private static String _msg;

    @Override
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，一般在这里面进行重连
        logger.info("连接断开，可以做重连");
        if (client == null || !client.isConnected()) {
            mqttConfig.getMqttPushClient();
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        // subscribe后得到的消息会执行到这里面
        logger.info("接收消息主题 : " + topic);
        logger.info("接收消息Qos : " + mqttMessage.getQos());
        logger.info("接收消息内容 : " + new String(mqttMessage.getPayload()));
//        byte[] payload = mqttMessage.getPayload();
//        System.out.println(payload);
//        System.out.println(mqttMessage.getPayload().getClass());
        _topic = topic;
        _qos = mqttMessage.getQos()+"";
        _msg = new String(mqttMessage.getPayload());

        //消息处理，topic、payload拆分
        YtMachineNew ytMachineNew = dealMachineNewMsg(topic, mqttMessage);
        //持久化操作，进行保存(日志表、数据表
        ytMachineNewMapper.insertYtMachineNew(ytMachineNew);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }

    //别的Controller层会调用这个方法来  获取  接收到的硬件数据
    public JSONObject receive() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("topic", _topic);
        jsonObject.put("qos", _qos);
        jsonObject.put("msg", _msg);
        return jsonObject;
    }

    public YtMachineNew dealMachineNewMsg(String topic, MqttMessage mqttMessage) {

        // 将主题信息 设置到 对象 字段
        String[] topics = topic.split("/");
        String IMEI = topics[2];
        String ICCID = topics[3];
        // 信号强度
        String CSQ = topics[4];
        String machine_name;
        String machine_type;

        // mqtt消息（json格式） 字符串 转 对象
        YtMachineNew ytMachineNew = new YtMachineNew();
        if (topics[0].equals("Sensor0")) {
            JSONObject mqttJSONObject = MqttUtils.parseSensorData(new String(mqttMessage.getPayload()));
            ytMachineNew = JSON.parseObject(String.valueOf(mqttJSONObject),YtMachineNew.class);
        }else {
            ytMachineNew = JSON.parseObject(new String(mqttMessage.getPayload()), YtMachineNew.class);
        }
        //logger.info(ytMachineNew.toString());

        ytMachineNew.setIMEI(IMEI);
        ytMachineNew.setICCID(ICCID);
        ytMachineNew.setLocationX(topics[4]);
        ytMachineNew.setLocationY(topics[5]);

        // 机器码machine_code计算(查设备表imei，存在取出code，不存在则生成code
        String machine_coe = device4gMapper.selectDevice4gByImei(topics[2]);
        if (machine_coe != null){
            ytMachineNew.setMachineCode(machine_coe);
        }else {
            // 第一次接入mqtt服务器，需新建设备，生成设备编码
            // 计算（自增
            int machine_num_code= Integer.parseInt(ytMachineNewMapper.findMaxMachineCode()) + 1;
            // 计算类型(有ph值是浮漂sensor：1开头；无则为控制器：2开头
//            if (ytMachineNew.getPh() == null) {
//                ytMachineNew.setMachineCode("24" + Integer.toString(machine_num_code));
//                machine_name = "1号控制器";
//                machine_type = "2";
//            }else {
//                ytMachineNew.setMachineCode("14" + Integer.toString(machine_num_code));
//                machine_name = "浮漂式传感器";
//                machine_type = "1";
//            }

            // 新设备 需存 设备表
            Device4g device4g = new Device4g();
            // 根据 topic[0]判断类型
            if (topics[0].equals("Sensor0")) {
                ytMachineNew.setMachineCode("14" + machine_num_code);
                machine_type = "浮漂式传感器";
            }else {
                machine_type = "4路控制器";
                ytMachineNew.setMachineCode("24" + machine_num_code);
            }
            device4g.setMachineCode(ytMachineNew.getMachineCode());
            device4g.setMachineType(machine_type);
            // 养殖场、状态 暂时 为 静态数据
            device4g.setFishPond("一号养殖场");
            // 设备命名
            machine_name = device4g.getFishPond() + device4gMapper.selectMaxIdByFishPond("一号养殖场") + "号机";
            device4g.setICCID(topics[3]);
            device4g.setIMEI(topics[2]);
            device4g.setMachineName(machine_name);
            device4g.setMachineStatus(0);
            device4g.setCSQ(CSQ);
            device4gMapper.insertDevice4g(device4g);
        }

        // 防止mqtt数据格式出错导致bug，先暂时赋值避免，后续做校验
        if (ytMachineNew.getPhase() == null) {
            ytMachineNew.setPhase(0);
        }
        if (ytMachineNew.getPower() == null) {
            ytMachineNew.setPower("0");
        }

        // 判断 设备状态 是否需要报警 存日志
        if (ytMachineNew.getPhase() != 0) {
            DayLog dayLog = new DayLog();
            Device4g device4g = device4gMapper.selectByMachineCode(machine_coe);
            BeanUtils.copyProperties(device4g,dayLog);
            dayLog.setId(null);
            dayLog.setCreateTime(new Date());
            String machineName = device4g.getMachineName();
            String warningMsg = machineName + warningUtils.parseType(ytMachineNew.getPhase());
            dayLog.setMsg(warningMsg);
            dayLog.setMsgType(1);
            dayLog.setPower(Float.valueOf(ytMachineNew.getPower()));
            logger.info((dayLog.toString()));
            dayLogMapper.insert(dayLog);
        }

        return ytMachineNew;
    }

}
