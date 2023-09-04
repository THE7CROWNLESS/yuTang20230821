package com.ruoyi.system.domain.yt;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class YtService  {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer user_id;

    private Integer machine_id;

    private String machine_code;

    private String machine_name;

    private Integer user_machine_id;

    private Integer pond_id;

    private String contact;

    private String phone;

    private String content;

    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime create_time;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime Update_time;

    private YtUser userInfo;


}
