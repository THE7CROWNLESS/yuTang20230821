package com.ruoyi.system.domain.yt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class YtMachine
{
    private static final long serialVersionUID = 1L;


    private Integer id;
    private String machine_code;
    private String machine_name;
    private Integer machine_type;
    private String IMEI;
    private String ICCID;
    private LocalDateTime connect_time;
    private LocalDateTime shut_time;
    private LocalDateTime create_time;
    private LocalDateTime over_date;
    private LocalDateTime first_bind_date;
    private LocalDateTime end_bind_date;
    private String speed;
    private String power;
    private Integer status;
    private String status_code;
    private Integer last_connect_time;
    private Integer if_show;
    private Integer using_userid;
    private Integer online_count;
    private Integer electric_count;
    private String qr_code;
    private LocalDateTime update_time;
    private String sev_ip;
    private String sev_prot;
    private String hardmodel;
    private String softver;
    private String protocol;
    private Integer stat;
    private String express_name;
    private String express_no;
    private Integer is_del;
    private BigDecimal degree;
    private BigDecimal oxygen;
    private Integer ox_machine_id;
    private BigDecimal critical;
    private BigDecimal x1_value;
    private BigDecimal x2_value;
    private BigDecimal r1_value;
    private BigDecimal r2_value;
    private Integer voltage;
    private BigDecimal current;
    private BigDecimal total_power;
    private Integer aerator_1_status;
    private Integer aerator_1_speed;
    private Integer aerator_2_status;
    private Integer aerator_2_speed;
    private Integer aerator_3_status;
    private Integer aerator_3_speed;
    private Integer aerator_4_status;
    private Integer aerator_4_speed;
    private Integer relay_1_status;
    private Integer relay_2_status;
    private Integer relay_3_status;
    private Integer relay_4_status;
    private String username;
    private String remark;

    private Integer is_online;

}
