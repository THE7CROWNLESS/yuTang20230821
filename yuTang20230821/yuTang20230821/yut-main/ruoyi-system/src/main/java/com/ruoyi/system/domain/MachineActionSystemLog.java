package com.ruoyi.system.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class MachineActionSystemLog {

    /**
     * 主键，唯一标识。自增
     */
    private Integer id;

    /**
     * IMEI
     */
    private String IMEI;

    /**
     * 设备id
     */
    private Integer machineId;

    /**
     * 设备名称
     */
    private String machineName;

    /**
     * 设备机器码
     */
    private String machineCode;

    /**
     * 手机ip
     */
    private String ip;

    /**
     * 发送指令
     */
    private String sendMsg;

    /**
     * 发送结果
     * 1.成功；0.失败
     */
    private Integer send;

    /**
     * 发送失败原因
     */
    private String error;

    /**
     * 管理员id
     */
    private Integer adminId;

    /**
     * 管理员名称
     */
    private String adminName;

    /**
     * 添加时间
     */
    private Integer createTime;

    /**
     * 更新时间
     */
    private Integer updateTime;
}
