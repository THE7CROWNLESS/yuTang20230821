package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.vo.Device4gNameVo;
import com.ruoyi.system.domain.vo.Device4gVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.Device4gMapper;
import com.ruoyi.system.domain.Device4g;
import com.ruoyi.system.service.IDevice4gService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-04
 */
@Service
public class Device4gServiceImpl implements IDevice4gService 
{
    @Autowired
    private Device4gMapper device4gMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Device4g selectDevice4gById(Integer id)
    {
        return device4gMapper.selectDevice4gById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param device4g 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Device4g> selectDevice4gList(Device4g device4g)
    {
        return device4gMapper.selectDevice4gList(device4g);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param device4g 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertDevice4g(Device4g device4g)
    {
        return device4gMapper.insertDevice4g(device4g);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param device4g 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateDevice4g(Device4g device4g)
    {
        return device4gMapper.updateDevice4g(device4g);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDevice4gByIds(Integer[] ids)
    {
        return device4gMapper.deleteDevice4gByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDevice4gById(Integer id)
    {
        return device4gMapper.deleteDevice4gById(id);
    }

    @Override
    public Device4gVo countStatus(String fishPond) {
        return device4gMapper.countStatus(fishPond);
    }

    @Override
    public List<Device4gNameVo> selectStatusList(Integer status,String fish_pond) {
        return device4gMapper.selectStatusList(status,fish_pond);
    }

    @Override
    public List<Device4gNameVo> selectAllStatusList(String fishPpond) {
        return device4gMapper.selectAllStatusList(fishPpond);
    }
}
