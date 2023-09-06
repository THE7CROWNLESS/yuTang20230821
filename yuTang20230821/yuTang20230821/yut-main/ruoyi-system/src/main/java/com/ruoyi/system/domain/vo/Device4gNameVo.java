package com.ruoyi.system.domain.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Device4gNameVo {
    private String fishPond;
    private String machineCode;
    private String machineName;
    private String machineType;
    private Integer machineStatus;
    // 信号强度
    private String CSQ;
}
