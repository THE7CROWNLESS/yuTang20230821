package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.YtMachineNewMapper;
import com.ruoyi.system.domain.YtMachineNew;
import com.ruoyi.system.service.IYtMachineNewService;

/**
 * 设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-02
 */
@Service
public class YtMachineNewServiceImpl implements IYtMachineNewService 
{
    @Autowired
    private YtMachineNewMapper ytMachineNewMapper;

    /**
     * 查询设备
     * 
     * @param id 设备主键
     * @return 设备
     */
    @Override
    public YtMachineNew selectYtMachineNewById(Integer id)
    {
        return ytMachineNewMapper.selectYtMachineNewById(id);
    }

    /**
     * 查询设备列表
     * 
     * @param ytMachineNew 设备
     * @return 设备
     */
    @Override
    public List<YtMachineNew> selectYtMachineNewList(YtMachineNew ytMachineNew)
    {
        return ytMachineNewMapper.selectYtMachineNewList(ytMachineNew);
    }

    /**
     * 新增设备
     * 
     * @param ytMachineNew 设备
     * @return 结果
     */
    @Override
    public int insertYtMachineNew(YtMachineNew ytMachineNew)
    {
        return ytMachineNewMapper.insertYtMachineNew(ytMachineNew);
    }

    /**
     * 修改设备
     * 
     * @param ytMachineNew 设备
     * @return 结果
     */
    @Override
    public int updateYtMachineNew(YtMachineNew ytMachineNew)
    {
        return ytMachineNewMapper.updateYtMachineNew(ytMachineNew);
    }

    /**
     * 批量删除设备
     * 
     * @param ids 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteYtMachineNewByIds(Integer[] ids)
    {
        return ytMachineNewMapper.deleteYtMachineNewByIds(ids);
    }

    /**
     * 删除设备信息
     * 
     * @param id 设备主键
     * @return 结果
     */
    @Override
    public int deleteYtMachineNewById(Integer id)
    {
        return ytMachineNewMapper.deleteYtMachineNewById(id);
    }
}
