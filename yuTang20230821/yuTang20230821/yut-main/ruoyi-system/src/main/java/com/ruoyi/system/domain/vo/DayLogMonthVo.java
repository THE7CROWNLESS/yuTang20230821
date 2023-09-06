package com.ruoyi.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DayLogMonthVo {
    private Integer month;
    private Integer power;
    private Integer operation;
    private Integer warning;
    private Integer year;
//    private Integer powerY;
//    private Integer operationY;
//    private Integer warningY;
}
