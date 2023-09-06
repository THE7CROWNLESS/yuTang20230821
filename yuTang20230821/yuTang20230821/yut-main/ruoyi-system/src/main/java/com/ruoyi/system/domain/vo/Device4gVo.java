package com.ruoyi.system.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Device4gVo {
    private String fishPond;
    private Integer deviceSum;
    private Integer deviceOn;
    private Integer deviceOff;
    private Integer deviceError;
    private Float voltageSum;
    private Integer warningSum;
    private Integer operationSum;
}
