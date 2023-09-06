package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.*;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sign.Md5Utils;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.YtAdminUserMapper;
import com.ruoyi.common.core.domain.entity.YtAdminUser;
import com.ruoyi.system.service.IYtAdminUserService;
import org.springframework.util.Assert;

/**
 * 后台管理员用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-21
 */
@Service
public class YtAdminUserServiceImpl implements IYtAdminUserService 
{
    @Autowired
    private YtAdminUserMapper ytAdminUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 查询后台管理员用户
     * 
     * @param id 后台管理员用户主键
     * @return 后台管理员用户
     */
    @Override
    public YtAdminUser selectYtAdminUserById(Long id)
    {
        return ytAdminUserMapper.selectYtAdminUserById(id);
    }

    @Override
    public YtAdminUser selectUserByUserName(String userName) {
        return ytAdminUserMapper.selectUserByUserName(userName);
    }

    /**
     * 查询后台管理员用户列表
     * 
     * @param ytAdminUser 后台管理员用户
     * @return 后台管理员用户
     */
    @Override
    public List<YtAdminUser> selectYtAdminUserList(YtAdminUser ytAdminUser)
    {
        return ytAdminUserMapper.selectYtAdminUserList(ytAdminUser);
    }

    @Override
    public List<YtAdminUserBody> selectYtAdminUserList(YtAdminUserBody body) {
        List<YtAdminUser> ytAdminUsers = ytAdminUserMapper.selectYtAdminUserList(new YtAdminUser());
        List<YtAdminUserBody> res = new ArrayList<>();
        // 把YtAdminUser转换成YtAdminUserBody
        for (int i = 0; i < ytAdminUsers.size(); i++) {
            YtAdminUser ytAdminUser = ytAdminUsers.get(i);
            YtAdminUserBody ytAdminUserBody = new YtAdminUserBody();
            ytAdminUserBody.setId(ytAdminUser.getId());
            ytAdminUserBody.setUsername(ytAdminUser.getUsername());
            ytAdminUserBody.setPassword(ytAdminUser.getPassword());
            ytAdminUserBody.setMobile(ytAdminUser.getMobile());
            ytAdminUserBody.setEmail(ytAdminUser.getEmail());
            ytAdminUserBody.setStatus(ytAdminUser.getStatus());
            ytAdminUserBody.setCreat_time(ytAdminUser.getCreate_time());
            ytAdminUserBody.setLast_Login_time(ytAdminUser.getLast_login_time());
            ytAdminUserBody.setLast_login_ip(ytAdminUser.getLast_login_ip());
            if (ytAdminUser.getRoles().size()>=1) {
                ytAdminUserBody.setGroup_Id(Long.valueOf(ytAdminUser.getRoles().get(0).getId()));
                ytAdminUserBody.setRole(ytAdminUser.getRoles().get(0).getTitle());
            }

            res.add(ytAdminUserBody);
        }
        return res;
    }

    @Override
    public List<YtAdminRedposeListBody> selectYtBody(YtAdminUser ytAdminUser) {
        List<YtAdminUser> list = this.selectYtAdminUserList(ytAdminUser);
        List<YtAdminRedposeListBody> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            YtAdminRedposeListBody ytAdminRedposeListBody = new YtAdminRedposeListBody();
            YtAdminUser ytAdminUser1 = list.get(i);

            ytAdminRedposeListBody.setId(ytAdminUser1.getId());
            if (ytAdminUser1.getRoles().size()>=1) {
                ytAdminRedposeListBody.setGroup_id(ytAdminUser1.getRoles().get(0).getId());
                ytAdminRedposeListBody.setRole(ytAdminUser1.getRoles().get(0).getTitle());
            }

            ytAdminRedposeListBody.setUsername(ytAdminUser1.getUsername());
            ytAdminRedposeListBody.setStatus(ytAdminUser1.getStatus());
            res.add(ytAdminRedposeListBody);
        }
        return res;
    }

    /**
     * 新增后台管理员用户
     * 
     * @param ytAdminUser 后台管理员用户
     * @return 结果
     */
    @Override
    public int insertYtAdminUser(YtAdminUser ytAdminUser)
    {
        ytAdminUser.setCreate_time(DateUtils.getNowDate());
        return ytAdminUserMapper.insertYtAdminUser(ytAdminUser);
    }

    @Override
    public YtAdminUser insertYtAdminUser(AddBody body) {
        if (!this.checkUserNameUnique(body.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }
        if (body.getUsername() == null) {
            throw new RuntimeException("用户名不能为空");
        }
        if (body.getPassword() == null) {
            throw new RuntimeException("密码不能为空");
        }
        if (body.getCheck_password() == null) {
            throw new RuntimeException("确认密码不能为空");
        }
        if (body.getGroup_id() == null) {
            throw new RuntimeException("角色不能为空");
        }
        YtAdminUser ytAdminUser = new YtAdminUser();
        ytAdminUser.setUsername(body.getUsername());
        if (body.getPassword()!= null && !body.getPassword().equals(body.getCheck_password())) {
            throw new RuntimeException("两次输入的密码不相同");
        }
        ytAdminUser.setCreate_time(DateUtils.getNowDate());
        ytAdminUser.setPassword(Md5Utils.hash(body.getPassword()));
        ytAdminUserMapper.insertYtAdminUser(ytAdminUser);

        // 将角色id和用户id插入到中间表
        ArrayList<SysUserRole> list = new ArrayList<>();
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(ytAdminUser.getId());
        sysUserRole.setRoleId(Long.valueOf(body.getGroup_id()));
        list.add(sysUserRole);
        sysUserRoleMapper.batchUserRole(list);

        return ytAdminUser;
    }

    /**
     * 修改后台管理员用户
     * 
     * @param ytAdminUser 后台管理员用户
     * @return 结果
     */
    @Override
    public int updateYtAdminUser(YtAdminUser ytAdminUser)
    {
        return ytAdminUserMapper.updateYtAdminUser(ytAdminUser);
    }

    @Override
    public boolean checkUserNameUnique(String username) {
        YtAdminUser ytAdminUser = new YtAdminUser();
        ytAdminUser.setUsername(username);
        ytAdminUser.setStatus(1);
        List<YtAdminUser> list = this.selectYtAdminUserList(ytAdminUser);
        if (list.size()>0) {
            return false;
        }

        return true;
    }

    @Override
    public int updateYtAdminUser(UpdateBody updatebody) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        YtAdminUser ytAdminUser = new YtAdminUser();
        ytAdminUser.setId(loginUser.getUserId());
        ytAdminUser.setUsername(updatebody.getUsername());
        if (updatebody.getPassword()!= null && !updatebody.getPassword().equals(updatebody.getCheck_password())) {
            throw new RuntimeException("两次输入的密码不相同");
        }
        if (updatebody.getPassword() != null) {
            ytAdminUser.setPassword(Md5Utils.hash(updatebody.getPassword()));
        }

        return this.updateYtAdminUser(ytAdminUser);
    }

    @Override
    public int updateYtAdminUser(UpdateUserBody body) {
        if (body.getId() == null) {
            throw new RuntimeException("用户id不能为空");
        }
        if (body.getUsername() == null) {
            throw new RuntimeException("用户名不能为空");
        }
        if (body.getGroup_id() == null) {
            throw new RuntimeException("角色不能为空");
        }
        if (body.getPassword() == null) {
            throw new RuntimeException("密码不能为空");
        }
        if (body.getCheck_password() == null) {
            throw new RuntimeException("确认密码不能为空");
        }
        if (body.getPassword()!= null && !body.getPassword().equals(body.getCheck_password())) {
            throw new RuntimeException("两次输入的密码不相同");
        }
        YtAdminUser ytAdminUser = new YtAdminUser();
        ytAdminUser.setId(Long.valueOf(body.getId()));
        ytAdminUser.setUsername(body.getUsername());
        ytAdminUser.setPassword(Md5Utils.hash(body.getPassword()));
        List<SysUserRole> list = new ArrayList<>();
        SysUserRole role = new SysUserRole();
        role.setUserId(Long.valueOf(body.getId()));
        role.setRoleId(Long.valueOf(body.getGroup_id()));
        list.add(role);
        sysUserRoleMapper.deleteUserRoleByUserId(Long.valueOf(body.getId()));
        sysUserRoleMapper.batchUserRole(list);
        ytAdminUserMapper.updateYtAdminUser(ytAdminUser);
        return 1;
    }

    /**
     * 批量删除后台管理员用户
     * 
     * @param ids 需要删除的后台管理员用户主键
     * @return 结果
     */
    @Override
    public int deleteYtAdminUserByIds(Long[] ids)
    {
        return ytAdminUserMapper.deleteYtAdminUserByIds(ids);
    }

    /**
     * 删除后台管理员用户信息
     * 
     * @param id 后台管理员用户主键
     * @return 结果
     */
    @Override
    public int deleteYtAdminUserById(Long id)
    {
        if (id == 1) {
            throw new RuntimeException("超级管理员无法删除");
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (loginUser.getUserId().equals(id)) {
            throw new RuntimeException("无法删除正在登录的用户");
        }
        // 删除用户和角色的关联
        sysUserRoleMapper.deleteUserRoleByUserId(id);
        return ytAdminUserMapper.deleteYtAdminUserById(id);
    }

    @Override
    public YtAdminRedposeListBody selectYtBodyById(Long id) {
        YtAdminUser ytAdminUser = this.selectYtAdminUserById(id);
        YtAdminRedposeListBody body = new YtAdminRedposeListBody();
        body.setId(id);
        if (ytAdminUser.getRoles().size()>=1) {
            body.setGroup_id(ytAdminUser.getRoles().get(0).getId());
        }
        body.setStatus(ytAdminUser.getStatus());
        body.setUsername(ytAdminUser.getUsername());

        return body;
    }
}
