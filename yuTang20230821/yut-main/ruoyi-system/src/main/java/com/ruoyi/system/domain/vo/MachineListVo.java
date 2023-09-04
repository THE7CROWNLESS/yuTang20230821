package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.system.handle.LocalDateTimeTypeHandler;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备列表查询Vo对象
 *
 * @author lls
 */
@Data
@ToString
public class MachineListVo implements Serializable {

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
    private Integer last_connect_time;

    /**
     * 是否显示
     * 1.显示；0.不显示
     */
    private Integer ifShow;

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
    private String soft_ver;

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
    private Integer ox_machine_id;

    /**
     * 警戒值
     */
    private Float critical;

    /**
     * 备注
     */
    private String remark;

    /**
     * 正在使用的用户名称
     */
    private String username;

    /**
     * 塘主名称
     */
    private String master;

    /**
     * 管理员名称
     */
    private String manager;

    /**
     * 是否在线
     * 1.在线；0.离线
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
