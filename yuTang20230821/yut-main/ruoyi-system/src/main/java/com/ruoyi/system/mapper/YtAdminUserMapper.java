package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.entity.YtAdminUser;

/**
 * 后台管理员用户Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-21
 */
public interface YtAdminUserMapper 
{
    /**
     * 查询后台管理员用户
     * 
     * @param id 后台管理员用户主键
     * @return 后台管理员用户
     */
    public YtAdminUser selectYtAdminUserById(Long id);

    public YtAdminUser selectUserByUserName(String userName);

    /**
     * 查询后台管理员用户列表
     * 
     * @param ytAdminUser 后台管理员用户
     * @return 后台管理员用户集合
     */
    public List<YtAdminUser> selectYtAdminUserList(YtAdminUser ytAdminUser);

    /**
     * 新增后台管理员用户
     * 
     * @param ytAdminUser 后台管理员用户
     * @return 结果
     */
    public int insertYtAdminUser(YtAdminUser ytAdminUser);

    /**
     * 修改后台管理员用户
     * 
     * @param ytAdminUser 后台管理员用户
     * @return 结果
     */
    public int updateYtAdminUser(YtAdminUser ytAdminUser);

    /**
     * 删除后台管理员用户
     * 
     * @param id 后台管理员用户主键
     * @return 结果
     */
    public int deleteYtAdminUserById(Long id);

    /**
     * 批量删除后台管理员用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYtAdminUserByIds(Long[] ids);


}
