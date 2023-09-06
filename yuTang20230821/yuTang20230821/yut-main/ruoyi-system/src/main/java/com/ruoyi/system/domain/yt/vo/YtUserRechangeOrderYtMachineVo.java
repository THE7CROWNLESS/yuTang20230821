package com.ruoyi.system.domain.yt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class YtUserRechangeOrderYtMachineVo {

    private Integer id;
    private String machine_code;
    private String machine_name;
    private Integer is_online=0;
}
