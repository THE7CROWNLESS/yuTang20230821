package com.ruoyi.system.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 分组管理设备实体类
 * @author lls
 */
@Data
@ToString
public class PondGroupMachine implements Serializable {

    /**
     * 主键，自增，唯一标识
     */
    private Integer id;

    /**
     * 用户id，相当于管理员id
     */
    private Integer userId;

    /**
     * 鱼塘id
     */
    private Integer pondId;

    /**
     * 用户关联设备id，对应的是user_machine表中的id字段
     */
    private Integer userMachineId;

    /**
     * 当前状态
     */
    private Integer status;

    /**
     * 是否为主人
     * 1.是；0.否
     */
    private Integer isMaster;

    /**
     * 主人id
     */
    private Integer masterUserId;

    /**
     * 创建时间，时间戳格式
     */
    private Integer createTime;

    /**
     * 更新时间，时间戳格式
     */
    private Integer updateTime;
}
