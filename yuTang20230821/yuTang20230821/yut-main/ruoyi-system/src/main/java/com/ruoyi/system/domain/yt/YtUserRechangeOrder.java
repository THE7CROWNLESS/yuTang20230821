package com.ruoyi.system.domain.yt;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户充值对象 yt_user_rechange_order
 * 
 * @author ruoyi
 * @date 2023-04-27
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class YtUserRechangeOrder
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String out_trade_no;
    private Integer user_id;
    private Integer machine_id;
    private BigDecimal price;
    private Integer valid_day;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime create_time;


    private String machine_code;
    private Integer user_machine_id;

    private Integer package_id;
    private Integer pay_time;

    private Integer status;




}
