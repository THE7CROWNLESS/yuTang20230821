package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DayLog {
    private Integer id;
    private String fishPond;
    private String machineCode;
    private String machineName;
    private Integer aerator;
    private String msg;
    private Float power;
    private Integer msgType;
    private Date createTime;
    private String field1;
    private String field2;
    private Integer field3;

}
