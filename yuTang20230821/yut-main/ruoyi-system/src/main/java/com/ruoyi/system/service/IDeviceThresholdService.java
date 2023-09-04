package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.DeviceThreshold;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-09-02
 */
public interface IDeviceThresholdService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public DeviceThreshold selectDeviceThresholdById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param deviceThreshold 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<DeviceThreshold> selectDeviceThresholdList(DeviceThreshold deviceThreshold);

    /**
     * 新增【请填写功能名称】
     * 
     * @param deviceThreshold 【请填写功能名称】
     * @return 结果
     */
    public int insertDeviceThreshold(DeviceThreshold deviceThreshold);

    /**
     * 修改【请填写功能名称】
     * 
     * @param deviceThreshold 【请填写功能名称】
     * @return 结果
     */
    public int updateDeviceThreshold(DeviceThreshold deviceThreshold);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteDeviceThresholdByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteDeviceThresholdById(Integer id);
}
