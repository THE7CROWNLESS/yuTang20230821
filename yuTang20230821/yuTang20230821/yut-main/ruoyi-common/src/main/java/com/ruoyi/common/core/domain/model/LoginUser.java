package com.ruoyi.common.core.domain.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.core.domain.entity.YtAdminUser;
import com.ruoyi.common.core.domain.entity.YtAuthRule;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * 登录用户身份权限
 *
 * @author ruoyi
 */
public class LoginUser implements UserDetails
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private YtAdminUser user;

    /**
     * 权限列表
     * @return
     */
    private List<YtAuthRule> nav = new ArrayList<>();



    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public List<YtAuthRule> getNav() {
        return this.nav;
    }

    public void setNav(List<YtAuthRule> _nav) {
        this.nav = _nav;
    }

    public LoginUser()
    {
    }

    public LoginUser(YtAdminUser user, Set<YtAuthRule> ytAuthRules)
    {
        this.user = user;
        this.permissions = permissions;
    }
    @JsonIgnore
    public boolean hasPar = false;
    public LoginUser(Long userId, Long deptId, YtAdminUser user, Set<YtAuthRule> ytAuthRules)
    {
        this.userId = userId;
        this.deptId = deptId;
        this.user = user;
        HashSet<String> permissions = new HashSet<>();
        YtAuthRule ytAuthRule = new YtAuthRule();
        ytAuthRule.setTitle("控制台");
        ytAuthRule.setHref("/admin/index/welcome");
        ArrayList<YtAuthRule> ytAuthRulesList = new ArrayList<>();
        ytAuthRulesList.addAll(ytAuthRules);
        this.nav.add(ytAuthRule);
        Collections.sort(ytAuthRulesList,(YtAuthRule o1,YtAuthRule o2)->(o1.getId()-o2.getId()));

        // 插入nav，nav实际上是一个森林，多个根节点，下面要用树遍历
        for (YtAuthRule yt : ytAuthRulesList) {
            yt.setHref("/"+yt.getHref());
            hasPar = false;
            int size = this.nav.size();
            for (int i = 0; i < size; i++) {
                dfsTree(this.nav.get(i), yt);
            }
            if (hasPar == false) {
                this.nav.add(yt);
            }
            permissions.add(yt.getHref());

        }
//        for (YtAuthRule yt : ytAuthRulesList) {
//            permissions.add(yt.getHref());
//            if (yt.getPid()==0) {
//                this.nav.add(yt);
//            }
//            else {
//                int size = this.nav.size();
//                boolean hasPar = false;
//                for (int i = 0; i < size; i++) {
//                    YtAuthRule rule = this.nav.get(i);
//
//                    if (rule.getId()!=null && rule.getId() == yt.getPid()) {
//                        hasPar = true;
//                        List<YtAuthRule> children = rule.getChildren();
//                        if (children == null) {
//                            children = new ArrayList<>();
//                        }
//                        children.add(yt);
//                        rule.setChildren(children);
//                        this.nav.set(i,rule);
//                    }
//
//                    List<YtAuthRule> children = rule.getChildren();
//
//                }
//                // 假设循环完没有找到父节点，直接加入。
//                if (!hasPar) {
//                    this.nav.add(yt);
//                }
//
//            }
//        }
        this.permissions = permissions;


    }

    public void dfsTree(YtAuthRule root, YtAuthRule rule) {
        if (root.getId()!= null && root.getId() .equals(rule.getPid()) ) {
            hasPar = true;
            List<YtAuthRule> children = root.getChildren();
            if (children == null) {
                children = new ArrayList<>();
            }
            children.add(rule);
            root.setChildren(children);

            return;
        }
        if (hasPar == false && root.getChildren() != null) {
            for (YtAuthRule child : root.getChildren()) {
                dfsTree(child,rule);
            }
        }

    }

    @JSONField(serialize = false)
    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getUsername();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public Long getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Long loginTime)
    {
        this.loginTime = loginTime;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation()
    {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation)
    {
        this.loginLocation = loginLocation;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public Long getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Long expireTime)
    {
        this.expireTime = expireTime;
    }

    public Set<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Set<String> permissions)
    {
        this.permissions = permissions;
    }

    public YtAdminUser getUser()
    {
        return user;
    }

    public void setUser(YtAdminUser user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }
}
