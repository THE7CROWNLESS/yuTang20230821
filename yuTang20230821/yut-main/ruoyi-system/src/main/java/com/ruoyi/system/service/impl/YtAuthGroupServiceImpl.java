package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.YtAuthGroup;
import com.ruoyi.system.mapper.YtAuthGroupMapper;
import com.ruoyi.system.service.IYtAuthGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenshijie
 * @date 2023/4/23 9:37
 */
@Service
public class YtAuthGroupServiceImpl implements IYtAuthGroupService {
    @Autowired
    private YtAuthGroupMapper ytAuthGroupMapper;

    /**
     * 查询权限组
     *
     * @param id 权限组主键
     * @return 权限组
     */
    @Override
    public YtAuthGroup selectYtAuthGroupById(Integer id)
    {
        return ytAuthGroupMapper.selectYtAuthGroupById(id);
    }

    /**
     * 查询权限组列表
     *
     * @param ytAuthGroup 权限组
     * @return 权限组
     */
    @Override
    public List<YtAuthGroup> selectYtAuthGroupList(YtAuthGroup ytAuthGroup)
    {
        return ytAuthGroupMapper.selectYtAuthGroupList(ytAuthGroup);
    }

    /**
     * 新增权限组
     *
     * @param ytAuthGroup 权限组
     * @return 结果
     */
    @Override
    public int insertYtAuthGroup(YtAuthGroup ytAuthGroup)
    {
        return ytAuthGroupMapper.insertYtAuthGroup(ytAuthGroup);
    }

    /**
     * 修改权限组
     *
     * @param ytAuthGroup 权限组
     * @return 结果
     */
    @Override
    public int updateYtAuthGroup(YtAuthGroup ytAuthGroup)
    {
        return ytAuthGroupMapper.updateYtAuthGroup(ytAuthGroup);
    }

    /**
     * 批量删除权限组
     *
     * @param ids 需要删除的权限组主键
     * @return 结果
     */
    @Override
    public int deleteYtAuthGroupByIds(Integer[] ids)
    {
        return ytAuthGroupMapper.deleteYtAuthGroupByIds(ids);
    }

    /**
     * 删除权限组信息
     *
     * @param id 权限组主键
     * @return 结果
     */
    @Override
    public int deleteYtAuthGroupById(Integer id)
    {
        return ytAuthGroupMapper.deleteYtAuthGroupById(id);
    }
}
