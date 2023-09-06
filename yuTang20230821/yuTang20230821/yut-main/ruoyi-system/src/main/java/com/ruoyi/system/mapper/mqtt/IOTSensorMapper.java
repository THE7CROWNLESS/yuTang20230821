package com.ruoyi.system.mapper.mqtt;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.mqtt.IOTSensor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Spring
 * @description 针对表【tb_sensor】的数据库操作Mapper
 * @createDate 2022-11-08 10:14:23
 * @Entity com.hua.iot.domain.IOTSensor
 */
@Mapper
public interface IOTSensorMapper extends BaseMapper<IOTSensor> {
    List<IOTSensor> getDistinctSensorByDeviceID(Integer deviceId);
}




