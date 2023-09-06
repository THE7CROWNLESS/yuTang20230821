package com.ruoyi.system.domain.query;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MachineEditQuery {

    /**
     * 设备id
     */
    private String id;

    /**
     * 机器类型
     * 1.增氧；2.投料；3.风扇；4.测氧仪；5.PH检测仪
     */
    private String machine_type;

    /**
     * 结束日期 格式为2021-07-13
     */
    private String over_date;

    /**
     * 计费结束时间，有时分秒，格式为2021-10-16 13:50:01
     */
    private String end_bind_date;

    /**
     * 备注
     */
    private String remark;
}
