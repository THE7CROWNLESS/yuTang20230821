package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: lyl
 * @Date: 2023/4/26 14:05
 * @Version: v1.0.0
 * @Description: 会员列表的实体类
 **/
@Data
public class Member {
    private String id;
    private String username;
    private String openid;
    private String phone;
    private String status;
    private String sex;
    private String avatar;
    private String province;
    private String city;
    private String district;
    private String country;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime create_time;
}
