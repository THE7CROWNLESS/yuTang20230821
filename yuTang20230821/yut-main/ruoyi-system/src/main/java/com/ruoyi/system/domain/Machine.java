package com.ruoyi.system.domain;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 鱼塘设备实体类
 * @author lls
 */
@Data
@ToString
public class Machine {

    /**
     * 设备id，自增，主键
     */
    private Integer id;

    /**
     * 机器码
     */
    private String machineCode;

    /**
     * 机器名
     */
    private String machineName;

    /**
     * 机器类型
     * 1.增氧；2.投料；3.风扇；4.测氧仪；5.PH检测仪；6.鱼塘控制器
     */
    private Integer machineType;

    /**
     * IMEI
     */
    private String IMEI;

    /**
     * ICCID
     */
    private String ICCID;

    /**
     * 连接时间
     */
    private LocalDateTime connectTime;

    /**
     * 断开时间
     */
    private LocalDateTime shutTime;

    /**
     * 添加时间（入库日期公用）
     */
    private Integer createTime;

    /**
     * 服务到期日期（也叫截止日期=入库+3年）
     */
    private Integer overDate;

    /**
     * 首次绑定日期（激活日期）
     */
    private LocalDateTime firstBindDate;

    /**
     * 计费结束日期，和firstBindDate相对应
     */
    private LocalDateTime endBindDate;

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
    private String statusCode;

    /**
     * 最后心跳时间
     */
    private Integer lastConnectTime;

    /**
     * 是否显示
     * 1.显示；0.不显示
     */
    private Integer ifShow;

    /**
     * 正在使用用户ID
     */
    private Integer usingUserid;

    /**
     * 累计时长，单位秒
     */
    private Integer onlineCount;

    /**
     * 累计耗电
     */
    private Integer electricCount;

    /**
     * 二维码
     */
    private String qrCode;

    /**
     * 更新时间
     */
    private Integer updateTime;

    /**
     * 设备连接ip地址
     */
    private String sevIp;

    /**
     * 设备连接端口号
     */
    private String sevPort;

    /**
     * 硬件型号
     */
    private String hardModel;

    /**
     * 软件版本
     */
    private String softVer;

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
    private String expressName;

    /**
     * 快递单号
     */
    private String expressNo;

    /**
     * 是否删除
     * 0.正常；1.删除
     */
    private Integer isDel;

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
    private Integer oxMachineId;

    /**
     * 警戒值
     */
    private Float critical;

    /**
     * X1
     */
    private Float x1Value;

    /**
     * X2
     */
    private Float x2Value;

    /**
     * R1
     */
    private Float r1Value;

    /**
     * R2
     */
    private Float r2Value;

    /**
     * 电网电压
     */
    private Integer voltage;

    /**
     * 电网电流
     */
    private Float current;

    /**
     * 控制柜总功率
     */
    private Float totalPower;

    /**
     * 控制柜1号增氧机
     * 0.停机；1.运行；3.故障
     */
    private Integer aerator1Status;

    /**
     * 控制柜1号增氧机转速
     */
    private Integer aerator1Speed;
    /**
     * 控制柜2号增氧机
     * 0.停机；1.运行；3.故障
     */
    private Integer aerator2Status;

    /**
     * 控制柜2号增氧机转速
     */
    private Integer aerator2Speed;
    /**
     * 控制柜3号增氧机
     * 0.停机；1.运行；3.故障
     */
    private Integer aerator3Status;

    /**
     * 控制柜3号增氧机转速
     */
    private Integer aerator3Speed;
    /**
     * 控制柜4号增氧机
     * 0.停机；1.运行；3.故障
     */
    private Integer aerator4Status;

    /**
     * 控制柜4号增氧机转速
     */
    private Integer aerator4Speed;

    /**
     * 控制柜1号继电器
     * 0.关闭；1.开启
     */
    private Integer relay1Status;

    /**
     * 控制柜2号继电器
     * 0.关闭；1.开启
     */
    private Integer relay2Status;

    /**
     * 控制柜3号继电器
     * 0.关闭；1.开启
     */
    private Integer relay3Status;
    /**
     * 控制柜4号继电器
     * 0.关闭；1.开启
     */
    private Integer relay4Status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 正在使用的用户名称
     */
    private String username;
}
