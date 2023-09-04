package com.ruoyi.system.domain.yt.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class YtUserRechangeOrderVo {

    private Integer id;

    private String out_trade_no;
    private Integer user_id;
    private Integer machine_id;
    private BigDecimal price;
    private Integer valid_day;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime create_time;

    private YtUserRechangeOrderYtUserVo user;
    private YtUserRechangeOrderYtMachineVo machine;

}
