package com.ruoyi.system.domain.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MachineElectricVo {

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



}
