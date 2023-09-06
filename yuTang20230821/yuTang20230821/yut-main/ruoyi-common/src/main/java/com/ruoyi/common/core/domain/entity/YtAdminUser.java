package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysRole;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * @author chenshijie
 * @date 2023/4/21 16:16
 */
@Data

@JsonIgnoreProperties({"admin"})
public class YtAdminUser {
    private static final long serialVersionUID = 1L;

//    @JsonIgnore
//    private Boolean admin;

    /** id */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;

    /** 手机 */
    @Excel(name = "手机")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String mobile;

    /** 邮箱 */
    @Excel(name = "邮箱")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String email;

    /** 用户状态 */
    @Excel(name = "用户状态")
    private Integer status;

    /** 最后登陆时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date last_login_time;

    /** 最后登录IP */
    @Excel(name = "最后登录IP")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String last_login_ip;

//    /** 角色对象 */
//    private List<SysRole> roles;
    /** 角色对象 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<YtAuthGroup> roles;

    /** 角色组 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long[] roleIds;

    /** 角色组 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long roleId;

    /** 岗位组 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long[] postIds;

    /** 角色名称 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String roleName;

    private Date create_time;



    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }



    public String getPassword()
    {
        return password;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMobile()
    {
        return mobile;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("username", getUsername())
                .append("password", getPassword())
                .append("mobile", getMobile())
                .append("email", getEmail())
                .append("status", getStatus())
                .toString();
    }

    public boolean isAdmin()
    {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1 == userId;
    }

    public List<YtAuthGroup> getRoles()
    {
        return roles;
    }

    public void setRoles(List<YtAuthGroup> roles)
    {
        this.roles = roles;
    }

    public Long[] getRoleIds()
    {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds)
    {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds()
    {
        return postIds;
    }

    public void setPostIds(Long[] postIds)
    {
        this.postIds = postIds;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }
}
