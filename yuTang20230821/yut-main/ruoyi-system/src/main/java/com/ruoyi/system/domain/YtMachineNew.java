package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备对象 yt_machine_new
 *
 * @author ruoyi
 * @date 2023-09-02
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class YtMachineNew extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 机器码 */
    @JSONField(name = "machine_code")
    @Excel(name = "机器码")
    private String machineCode;

    /** IMEI */
    @Excel(name = "IMEI")
    private String IMEI;

    /** ICCID */
    @Excel(name = "ICCID")
    private String ICCID;

    /** 电网电压 */
    @Excel(name = "电网电压")
    private Integer voltage;

    /** 0:正常；1：缺 A 相；2：缺 B 相；3：缺 C 相 */
    @Excel(name = "0:正常；1：缺 A 相；2：缺 B 相；3：缺 C 相")
    private Integer phase;

    /** 功率 */
    @Excel(name = "功率")
    private String power;

    /** 电网电流 */
    @Excel(name = "电网电流")
    private Float current;

    /** 累计耗电Power Consumption， 单位千瓦时 KWh */
    @Excel(name = "累计耗电Power Consumption， 单位千瓦时 KWh")
    private Integer energy;

    /** 第1路电机的模式 */
    @Excel(name = "第1路电机的模式")
    @JSONField(name = "x1_model")
    private Integer x1Model;

    /** 第2路电机的模式 */
    @Excel(name = "第2路电机的模式")
    @JSONField(name = "x2_model")
    private Integer x2Model;

    /** 第3路电机的模式 */
    @Excel(name = "第3路电机的模式")
    @JSONField(name = "x4_model")
    private Integer x4Model;

    /** 第4路电机的模式 */
    @Excel(name = "第4路电机的模式")
    @JSONField(name = "x3_model")
    private Integer x3Model;

    /** 控制柜1号增氧机转速 */
    @Excel(name = "控制柜1号增氧机转速")
    @JSONField(name = "aerator_1_speed")
    private Integer aerator1Speed;

    /** 控制柜1号增氧机： 0关机 1开机 2故障 */
    @Excel(name = "控制柜1号增氧机： 0关机 1开机 2故障")
    @JSONField(name = "aerator_1_status")
    private Integer aerator1Status;

    /** 控制柜2号增氧机转速 */
    @Excel(name = "控制柜2号增氧机转速")
    @JSONField(name = "aerator_2_speed")
    private Integer aerator2Speed;

    /** 控制柜2号增氧机： 0关机 1开机 2故障 */
    @Excel(name = "控制柜2号增氧机： 0关机 1开机 2故障")
    @JSONField(name = "aerator_2_status")
    private Integer aerator2Status;

    /** 控制柜3号增氧机转速 */
    @Excel(name = "控制柜3号增氧机转速")
    @JSONField(name = "aerator_3_speed")
    private Integer aerator3Speed;

    /** 控制柜3号增氧机： 0关机 1开机 2故障 */
    @Excel(name = "控制柜3号增氧机： 0关机 1开机 2故障")
    @JSONField(name = "aerator_3_status")
    private Integer aerator3Status;

    /** 控制柜4号增氧机转速 */
    @Excel(name = "控制柜4号增氧机转速")
    @JSONField(name = "aerator_4_speed")
    private Integer aerator4Speed;

    /** 控制柜4号增氧机： 0关机 1开机 2故障 */
    @Excel(name = "控制柜4号增氧机： 0关机 1开机 2故障")
    @JSONField(name = "aerator_3_status")
    private Integer aerator4Status;

    /** 连接时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "连接时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JSONField(name = "connect_time")
    private Date connectTime;

    /** 增氧仪设备ID  测氧仪需要关联  machine_type=4 */
    @Excel(name = "增氧仪设备ID  测氧仪需要关联  machine_type=4")
    @JSONField(name = "ox_machine_id")
    private Integer oxMachineId;

    /** 溶氧值 */
    @Excel(name = "溶氧值")
    private Float oxygen;

    /** 警戒值 */
    @Excel(name = "警戒值")
    @JSONField(name = "oxy_critical")
    private Float oxyCritical;

    /** 温度 */
    @Excel(name = "温度")
    private Float temperature;

    /** remark */
    @Excel(name = "remark")
    private String remark;

    /** 正在使用用户ID */
    @Excel(name = "正在使用用户ID")
    private Long userid;

    /** 正在使用的用户名称 */
    @Excel(name = "正在使用的用户名称")
    private String username;

    /** 经度 */
    @Excel(name = "经度")
    @JSONField(name = "location_x")
    private String locationX;

    /** 纬度 */
    @Excel(name = "纬度")
    @JSONField(name = "location_y")
    private String locationY;

    /** 浮漂式Sensor的PH */
    @Excel(name = "PH")
    private Float ph;

    /** 浮漂式Sensor的大气压 */
    @Excel(name = "atmosphere")
    private Float atmosphere;

}
