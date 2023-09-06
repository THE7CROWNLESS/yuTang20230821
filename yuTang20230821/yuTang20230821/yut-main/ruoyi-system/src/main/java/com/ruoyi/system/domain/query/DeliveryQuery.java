package com.ruoyi.system.domain.query;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeliveryQuery {

    /**
     * 设备id
     */
    private Integer id;

    /**
     * 物流公司名称
     */
    private String express_name;

    /**
     * 物流单号
     */
    private String express_no;
}
