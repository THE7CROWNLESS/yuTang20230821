package com.ruoyi.system.domain.yt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class YtPackage
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer type;

    private String title;

    private BigDecimal origin_price;

    private BigDecimal pay_price;


    private Integer valid_day;

    private Integer status;

    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime create_time;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime update_time;

}
