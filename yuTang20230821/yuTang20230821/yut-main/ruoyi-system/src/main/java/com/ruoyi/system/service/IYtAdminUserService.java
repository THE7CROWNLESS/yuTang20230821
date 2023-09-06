package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.entity.YtAdminUser;
import com.ruoyi.common.core.domain.model.*;

/**
 * 后台管理员用户Service接口
 * 
 * @author ruoyi
 * @date 2023-04-21
 */
public interface IYtAdminUserService 
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

    public List<YtAdminUserBody> selectYtAdminUserList(YtAdminUserBody ytAdminUser);

    public List<YtAdminRedposeListBody> selectYtBody(YtAdminUser ytAdminUser);

    /**
     * 新增后台管理员用户
     * 
     * @param ytAdminUser 后台管理员用户
     * @return 结果
     */
    public int insertYtAdminUser(YtAdminUser ytAdminUser);

    /**
     * 后台管理员添加
     */
    public YtAdminUser insertYtAdminUser(AddBody ytAdminUser);

    /**
     * 修改后台管理员用户
     * 
     * @param ytAdminUser 后台管理员用户
     * @return 结果
     */
    public int updateYtAdminUser(YtAdminUser ytAdminUser);

    /**
     * 校验用户名称是否唯一
     *
     * @param username 用户信息
     * @return 结果
     */
    public boolean checkUserNameUnique(String username);

    public int updateYtAdminUser(UpdateBody ytAdminUser);

    public int updateYtAdminUser(UpdateUserBody ytAdminUser);

    /**
     * 批量删除后台管理员用户
     * 
     * @param ids 需要删除的后台管理员用户主键集合
     * @return 结果
     */
    public int deleteYtAdminUserByIds(Long[] ids);

    /**
     * 删除后台管理员用户信息
     * 
     * @param id 后台管理员用户主键
     * @return 结果
     */
    public int deleteYtAdminUserById(Long id);

    YtAdminRedposeListBody selectYtBodyById(Long id);
}
