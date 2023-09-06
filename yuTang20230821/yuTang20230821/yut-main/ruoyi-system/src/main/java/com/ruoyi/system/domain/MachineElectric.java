package com.ruoyi.system.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MachineElectric {

    /**
     * 主键，自增，唯一标识
     */
    private Integer id;

    /**
     * 对应当前用户绑定设备ID
     */
    private Integer user_machine_id;

    /**
     * 所属鱼塘id
     */
    private Integer pond_id;

    /**
     * 设备id
     */
    private Integer machine_id;

    /**
     * 当月新增耗电量
     */
    private Integer electric;

    /**
     * 累计耗电量
     */
    private Integer total_electric;

    /**
     * 年份
     */
    private String year;

    /**
     * 月份
     */
    private String month;

    /**
     * 日期
     */
    private String date;

    /**
     * 添加时间
     */
    private Integer create_time;

    /**
     * 更新时间
     */
    private Integer update_time;


}
