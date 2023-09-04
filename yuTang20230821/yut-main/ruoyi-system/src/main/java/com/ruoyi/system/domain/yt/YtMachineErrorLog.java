package com.ruoyi.system.domain.yt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class YtMachineErrorLog
{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer user_machine_id;
    private Integer pond_id;
    private String pond_title;
    private Integer machine_id;
    private String machine_code;
    private String machine_name;
    private String IMEI;
    private String error_reason;
    private Integer error_type;
    private Integer status;
    private String solve;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime solve_time;
    private String note;
    private String error_code;

    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime create_time;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime update_time;

}
