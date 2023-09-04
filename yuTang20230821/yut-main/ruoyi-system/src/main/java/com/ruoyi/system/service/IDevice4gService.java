package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Device4g;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-09-04
 */
public interface IDevice4gService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Device4g selectDevice4gById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param device4g 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Device4g> selectDevice4gList(Device4g device4g);

    /**
     * 新增【请填写功能名称】
     * 
     * @param device4g 【请填写功能名称】
     * @return 结果
     */
    public int insertDevice4g(Device4g device4g);

    /**
     * 修改【请填写功能名称】
     * 
     * @param device4g 【请填写功能名称】
     * @return 结果
     */
    public int updateDevice4g(Device4g device4g);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteDevice4gByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteDevice4gById(Integer id);
}
