package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 device_4g
 * 
 * @author ruoyi
 * @date 2023-09-04
 */
@Data
public class Device4g extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 机器码 */
    @Excel(name = "机器码")
    private String machineCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String machineName;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String machineType;

    /** 养殖场名称 */
    @Excel(name = "养殖场名称")
    private String fishPond;

    /** 设备状态：0在线，1离线，2故障 */
    @Excel(name = "设备状态：0在线，1离线，2故障")
    private Integer machineStatus;

    /** IMEI */
    @Excel(name = "IMEI")
    private String IMEI;

    /** ICCID */
    @Excel(name = "ICCID")
    private String ICCID;

    /** 信号强度 */
    @Excel(name = "CSQ")
    private String CSQ;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("machineCode", getMachineCode())
            .append("machineName", getMachineName())
            .append("machineType", getMachineType())
            .append("fishPond", getFishPond())
            .append("machineStatus", getMachineStatus())
            .append("IMEI", getIMEI())
            .append("ICCID", getICCID())
            .append("CSQ",getCSQ())
            .toString();
    }
}
