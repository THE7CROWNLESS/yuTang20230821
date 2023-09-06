package com.ruoyi.system.domain.yt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class YtUser
{
    private static final long serialVersionUID = 1L;


    private Integer id;

    private String username;

    private String nickname;

    private String openid;

    private String phone;

    private Integer user_type;

    private Integer leader;

    private Integer status;

    private Integer last_login;

    private Integer login_num;

    private String last_ip;

    private Integer mobile_validated;

    private String oauth;

    private String password;

    private String paypwd;

    private String unionid;

    private Integer sex;

    private String avatar;

    private String province;

    private String city;

    private String district;

    private String country;

    private String token;

    private String invite_code;

    private String alias_name;

    private String session_key;

    private String auth_type;

    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime create_time;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime update_time;

}
