package com.ruoyi.system.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户与设备绑定类
 * @author lls
 */
@Data
public class UserMachine implements Serializable {

    /**
     * 主键，id，唯一标识，自增
     */
    private Integer id;

    /**
     * 设备id
     */
    private Integer machineId;

    /**
     * 设备类型，同Machine中的字段
     */
    private Integer machineType;

    /**
     * 设备码
     */
    private String machineCode;

    /**
     * 设备名称
     */
    private String machineName;

    /**
     * 鱼塘名称
     */
    private String pondTitle;

    /**
     * 鱼塘ID
     */
    private Integer pondId;

    /**
     * 绑定的用户id，根据该id可以从user表中获取到对应的用户信息
     */
    private Integer userId;

    /**
     * 对应的组id
     */
    private Integer groupId;

    /**
     * 是否删除
     * 0.正常；1.删除
     */
    private Integer isDel;

    /**
     * 通知优先级
     * 1.塘主；2.管理员
     */
    private Integer priority;

    /**
     * 是否开启离线预警
     * 0.否；1.是
     */
    private Integer isOfflineWarn;

    /**
     * 是否开启故障预警
     * 0.否；1.是
     */
    private Integer isErrorWarn;

    /**
     * 添加时间，时间戳格式
     */
    private Integer createTime;

    /**
     * 更新时间，时间戳格式
     */
    private Integer updateTime;


}
