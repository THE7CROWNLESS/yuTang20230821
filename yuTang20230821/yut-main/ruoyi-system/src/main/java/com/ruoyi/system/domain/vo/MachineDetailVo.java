package com.ruoyi.system.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备详情Vo
 *
 * @author lls
 */
@Data
@ToString
public class MachineDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 设备id，自增，主键
     */
    private Integer id;

    /**
     * 机器码
     */
    private String machine_code;

    /**
     * 机器名
     */
    private String machine_name;

    /**
     * 机器类型
     * 1.增氧；2.投料；3.风扇；4.测氧仪；5.PH检测仪；6.鱼塘控制器
     */
    private Integer machine_type;

    /**
     * IMEI
     */
    @JsonProperty("IMEI")
    private String IMEI;

    /**
     * ICCID
     */
    @JsonProperty("ICCID")
    private String ICCID;

    /**
     * 连接时间
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime connect_time;

    /**
     * 断开时间
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime shut_time;

    /**
     * 添加时间（入库日期公用）
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime create_time;

    /**
     * 服务到期日期（也叫截止日期=入库+3年）
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime over_date;

    /**
     * 首次绑定日期（激活日期）
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime first_bind_date;

    /**
     * 计费结束日期，和firstBindDate相对应
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime end_bind_date;

    /**
     * 转速
     */
    private String speed;

    /**
     * 功率
     */
    private String power;

    /**
     * 设备状态
     * 0.离线；1.运行；2.停机；3.故障
     */
    private Integer status;

    /**
     * 状态异常码
     */
    private String status_code;

    /**
     * 最后心跳时间
     */
//    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Integer last_connect_time;

    /**
     * 是否显示
     * 1.显示；0.不显示
     */
    private Integer if_show;

    /**
     * 正在使用用户ID
     */
    private Integer using_userid;

    /**
     * 累计时长，单位秒
     */
    private Integer online_count;

    /**
     * 累计耗电
     */
    private Integer electric_count;

    /**
     * 二维码
     */
    private String qr_code;

    /**
     * 更新时间
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime update_time;

    /**
     * 设备连接ip地址
     */
    private String sev_ip;

    /**
     * 设备连接端口号
     */
    private String sev_port;

    /**
     * 硬件型号
     */
    private String hardmodel;

    /**
     * 软件版本
     */
    private String softver;

    /**
     * 通讯协议版本
     */
    private String protocol;

    /**
     * 设备状态
     * 0.待入库；1.已入库
     */
    private Integer stat;

    /**
     * 快递名称
     */
    private String express_name;

    /**
     * 快递单号
     */
    private String express_no;

    /**
     * 是否删除
     * 0.正常；1.删除
     */
    private Integer is_del;

    /**
     * 温度
     */
    private Float degree;

    /**
     * 溶氧值
     */
    private Float oxygen;

    /**
     * 增氧仪设备ID，测氧仪需要关联，当machineType为4的时候需要进行关联
     */
    private Integer ox_machine_d;

    /**
     * 警戒值
     */
    private Float critical;

    /**
     * X1
     */
    private Float x1_value;

    /**
     * X2
     */
    private Float x2_value;

    /**
     * R1
     */
    private Float r1_value;

    /**
     * R2
     */
    private Float r2_value;

    /**
     * 缺少的字段：
     *         "voltage": 387,
     *         "current": 4.7,
     *         "total_power": 3.5,
     *         "aerator_1_status": 0,
     *         "aerator_1_speed": 0,
     *         "aerator_2_status": 1,
     *         "aerator_2_speed": 160,
     *         "aerator_3_status": 3,
     *         "aerator_3_speed": 0,
     *         "aerator_4_status": 1,
     *         "aerator_4_speed": 80,
     *         "relay_1_status": null,
     *         "relay_2_status": null,
     *         "relay_3_status": null,
     *         "relay_4_status": null,
     */

    /**
     * 备注
     */
    private String remark;

    /**
     * 正在使用的用户名称
     */
    private String username;

    /**
     * 任务信息
     */
    private String task;

    /**
     * 塘主
     */
    private String master;

    /**
     * 管理员
     */
    private String manager;

    /**
     * 鱼塘名称
     */
    private String pond_title;

    /**
     * 是否在线
     */
    private Integer is_online;

    /**
     * 上线时间
     */

    private Integer online_time;

    /**
     * 离线时间
     */
    private Integer offline_time;

    private Integer is_over;

}
