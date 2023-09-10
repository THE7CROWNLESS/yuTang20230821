package com.ruoyi.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OxygenAutoVo {
    private String machineCode;
    private Float oxygen1;
    private Float oxygen2;

    // 默认设置联动值
    public OxygenAutoVo(String machineCode) {
        this.machineCode = machineCode;
        this.oxygen1 = Float.valueOf(0);
        this.oxygen2 = Float.valueOf(10);
    }
}
