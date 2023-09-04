package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DeviceThresholdMapper;
import com.ruoyi.system.domain.DeviceThreshold;
import com.ruoyi.system.service.IDeviceThresholdService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-02
 */
@Service
public class DeviceThresholdServiceImpl implements IDeviceThresholdService 
{
    @Autowired
    private DeviceThresholdMapper deviceThresholdMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public DeviceThreshold selectDeviceThresholdById(Integer id)
    {
        return deviceThresholdMapper.selectDeviceThresholdById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param deviceThreshold 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<DeviceThreshold> selectDeviceThresholdList(DeviceThreshold deviceThreshold)
    {
        return deviceThresholdMapper.selectDeviceThresholdList(deviceThreshold);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param deviceThreshold 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertDeviceThreshold(DeviceThreshold deviceThreshold)
    {
        return deviceThresholdMapper.insertDeviceThreshold(deviceThreshold);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param deviceThreshold 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateDeviceThreshold(DeviceThreshold deviceThreshold)
    {
        return deviceThresholdMapper.updateDeviceThreshold(deviceThreshold);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDeviceThresholdByIds(Integer[] ids)
    {
        return deviceThresholdMapper.deleteDeviceThresholdByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDeviceThresholdById(Integer id)
    {
        return deviceThresholdMapper.deleteDeviceThresholdById(id);
    }
}
