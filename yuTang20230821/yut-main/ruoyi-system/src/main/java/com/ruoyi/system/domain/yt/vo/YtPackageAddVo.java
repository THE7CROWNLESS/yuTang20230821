package com.ruoyi.system.domain.yt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class YtPackageAddVo {

    private String title;

    private BigDecimal origin_price;

    private BigDecimal pay_price;


    private Integer valid_day;


}
