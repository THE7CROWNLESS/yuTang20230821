package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 day_records
 * 
 * @author ruoyi
 * @date 2023-09-02
 */
public class DayRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 机器码 */
    @Excel(name = "机器码")
    private String machineCode;

    /** 累计耗电Power Consumption， 单位千瓦时 KWh */
    @Excel(name = "累计耗电Power Consumption， 单位千瓦时 KWh")
    private Integer energy;

    /** 操作次数 */
    @Excel(name = "操作次数")
    private Integer operateNums;

    /** 警告次数 */
    @Excel(name = "警告次数")
    private Integer warningNums;

    /** 记录消息内容 */
    @Excel(name = "记录消息内容")
    private String msgDetails;

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
    public void setEnergy(Integer energy) 
    {
        this.energy = energy;
    }

    public Integer getEnergy() 
    {
        return energy;
    }
    public void setOperateNums(Integer operateNums) 
    {
        this.operateNums = operateNums;
    }

    public Integer getOperateNums() 
    {
        return operateNums;
    }
    public void setWarningNums(Integer warningNums) 
    {
        this.warningNums = warningNums;
    }

    public Integer getWarningNums() 
    {
        return warningNums;
    }
    public void setMsgDetails(String msgDetails) 
    {
        this.msgDetails = msgDetails;
    }

    public String getMsgDetails() 
    {
        return msgDetails;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("machineCode", getMachineCode())
            .append("energy", getEnergy())
            .append("operateNums", getOperateNums())
            .append("warningNums", getWarningNums())
            .append("msgDetails", getMsgDetails())
            .toString();
    }
}
