package com.ruoyi.system.domain.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommandVo {

    /**
     * 设备id
     */
    private String id;

    /**
     * 16进制码
     */
    private String code;
}
