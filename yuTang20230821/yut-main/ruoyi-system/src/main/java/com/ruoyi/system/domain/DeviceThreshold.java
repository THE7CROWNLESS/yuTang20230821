package com.ruoyi.system.domain;

import java.math.BigDecimal;
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
    private BigDecimal current;

    /** 溶氧值上限 */
    @Excel(name = "溶氧值上限")
    private BigDecimal oxygenMax;

    /** 溶氧值下限 */
    @Excel(name = "溶氧值下限")
    private BigDecimal oxygenMin;

    /** 温度上限 */
    @Excel(name = "温度上限")
    private BigDecimal temperatureMax;

    /** 温度下限 */
    @Excel(name = "温度下限")
    private BigDecimal temperatureMin;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setMachineCode(String machineCode) 
    {
        this.machineCode = machineCode;
    }

    public String getMachineCode() 
    {
        return machineCode;
    }
    public void setVoltageMax(Integer voltageMax) 
    {
        this.voltageMax = voltageMax;
    }

    public Integer getVoltageMax() 
    {
        return voltageMax;
    }
    public void setVoltageMin(Integer voltageMin) 
    {
        this.voltageMin = voltageMin;
    }

    public Integer getVoltageMin() 
    {
        return voltageMin;
    }
    public void setCurrent(BigDecimal current) 
    {
        this.current = current;
    }

    public BigDecimal getCurrent() 
    {
        return current;
    }
    public void setOxygenMax(BigDecimal oxygenMax) 
    {
        this.oxygenMax = oxygenMax;
    }

    public BigDecimal getOxygenMax() 
    {
        return oxygenMax;
    }
    public void setOxygenMin(BigDecimal oxygenMin) 
    {
        this.oxygenMin = oxygenMin;
    }

    public BigDecimal getOxygenMin() 
    {
        return oxygenMin;
    }
    public void setTemperatureMax(BigDecimal temperatureMax) 
    {
        this.temperatureMax = temperatureMax;
    }

    public BigDecimal getTemperatureMax() 
    {
        return temperatureMax;
    }
    public void setTemperatureMin(BigDecimal temperatureMin) 
    {
        this.temperatureMin = temperatureMin;
    }

    public BigDecimal getTemperatureMin() 
    {
        return temperatureMin;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("machineCode", getMachineCode())
            .append("voltageMax", getVoltageMax())
            .append("voltageMin", getVoltageMin())
            .append("current", getCurrent())
            .append("oxygenMax", getOxygenMax())
            .append("oxygenMin", getOxygenMin())
            .append("temperatureMax", getTemperatureMax())
            .append("temperatureMin", getTemperatureMin())
            .toString();
    }
}
