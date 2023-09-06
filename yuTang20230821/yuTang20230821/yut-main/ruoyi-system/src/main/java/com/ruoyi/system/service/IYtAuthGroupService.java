package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.YtAuthGroup;

import java.util.List;

/**
 * @author chenshijie
 * @date 2023/4/23 9:37
 */

public interface IYtAuthGroupService {
    /**
     * 查询权限组
     *
     * @param id 权限组主键
     * @return 权限组
     */
    public YtAuthGroup selectYtAuthGroupById(Integer id);

    /**
     * 查询权限组列表
     *
     * @param ytAuthGroup 权限组
     * @return 权限组集合
     */
    public List<YtAuthGroup> selectYtAuthGroupList(YtAuthGroup ytAuthGroup);

    /**
     * 新增权限组
     *
     * @param ytAuthGroup 权限组
     * @return 结果
     */
    public int insertYtAuthGroup(YtAuthGroup ytAuthGroup);

    /**
     * 修改权限组
     *
     * @param ytAuthGroup 权限组
     * @return 结果
     */
    public int updateYtAuthGroup(YtAuthGroup ytAuthGroup);

    /**
     * 批量删除权限组
     *
     * @param ids 需要删除的权限组主键集合
     * @return 结果
     */
    public int deleteYtAuthGroupByIds(Integer[] ids);

    /**
     * 删除权限组信息
     *
     * @param id 权限组主键
     * @return 结果
     */
    public int deleteYtAuthGroupById(Integer id);
}
