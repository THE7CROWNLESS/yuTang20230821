package com.ruoyi.system.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 device_threshold
 *
 * @author ruoyi
 * @date 2023-09-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeviceThreshold extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 机器码 */
    @Excel(name = "机器码")
    private String machineCode;

    /** 电网电压上限 */
    @Excel(name = "电网电压上限")
    private Integer voltageMax;

    /** 电网电压下限 */
    @Excel(name = "电网电压下限")
    private Integer voltageMin;

    /** 电网电流阈值 */
    @Excel(name = "电网电流阈值")
    private Float current;

    /** 溶氧值上限 */
    @Excel(name = "溶氧值上限")
    private Float oxygenMax;

    /** 溶氧值下限 */
    @Excel(name = "溶氧值下限")
    private Float oxygenMin;

    /** 温度上限 */
    @Excel(name = "温度上限")
        private Float temperatureMax;

    /** 温度下限 */
    @Excel(name = "温度下限")
    private Float temperatureMin;

}
