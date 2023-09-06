package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("device_4g")
public class Device_4g {
    @TableField("id")
    private int id;
    @TableField("machine_code")
    private String machineCode;
    @TableField("machine_name")
    private String machineName;
    @TableField("machine_type")
    private String machineType;
    @TableField("fish_pond")
    private String fishPond;
    @TableField("machine_status")
    private String machineStatus;
    @TableField("IMEI")
    private String IMEI;
    @TableField("ICCID")
    private String ICCID;
}
