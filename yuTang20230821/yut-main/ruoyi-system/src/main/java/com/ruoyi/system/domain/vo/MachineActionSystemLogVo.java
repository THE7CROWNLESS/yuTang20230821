package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@ToString
@Accessors(chain = true)
public class MachineActionSystemLogVo {

    /**
     * 主键，唯一标识。自增
     */
    private Integer id;

    /**
     * IMEI
     */
    @JsonProperty("IMEI")
    private String IMEI;

    /**
     * 设备id
     */
    private Integer machine_id;

    /**
     * 设备名称
     */
    private String machine_name;

    /**
     * 设备机器码
     */
    private String machine_code;


    /**
     * 发送指令
     */
    private String send_msg;


    /**
     * 管理员id
     */
    private Integer admin_id;

    /**
     * 管理员名称
     */
    private String admin_name;

    /**
     * 添加时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime create_time;
}
