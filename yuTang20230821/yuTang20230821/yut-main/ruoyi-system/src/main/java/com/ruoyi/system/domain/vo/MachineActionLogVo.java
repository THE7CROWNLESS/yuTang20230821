package com.ruoyi.system.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class MachineActionLogVo {

    /**
     * 主键，唯一标识，自增
     */
    private Integer id;

    /**
     * 用户关联设备id
     */
    private Integer userMachine_id;

    /**
     * 设备ID
     */
    private Integer machine_id;

    /**
     * 设备名称
     */
    private String machine_name;

    /**
     * 设备码
     */
    private String machine_code;

    /**
     * 用户ID
     */
    private Integer user_id;

    /**
     * 用户名称
     */
    private String nickname;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 操作说明
     */
    private String action;

    /**
     * 操作时间
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime create_time;

}
