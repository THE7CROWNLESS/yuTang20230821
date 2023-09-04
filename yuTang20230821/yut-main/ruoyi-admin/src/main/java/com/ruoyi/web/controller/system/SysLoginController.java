package com.ruoyi.web.controller.system;

import java.util.*;

import com.ruoyi.common.core.domain.entity.YtAdminUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.domain.model.UpdateBody;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.to.LoginResonseBody;
import com.ruoyi.system.service.IYtAdminUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.system.service.ISysMenuService;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/admin")
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    IYtAdminUserService ytAdminUserService;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        System.out.println("============login chen");
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = IpUtils.getIpAddr();
        Map<String, Object> data = new HashMap<>();
        AjaxResult ajax = new AjaxResult(200,"登录成功");
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        LoginUser loginUser = tokenService.getLoginUser(token);
        LoginResonseBody loginResonseBody = new LoginResonseBody();
        loginResonseBody.setId(loginUser.getUserId());
        loginResonseBody.setUsername(loginUser.getUsername());
        YtAdminUser ytAdminUser = new YtAdminUser();
        ytAdminUser.setId(loginUser.getUserId());
        ytAdminUser.setLast_login_ip(ip);
        // 往yt_admin_user表中更新最后登录时间

        ytAdminUser.setLast_login_time(new Date());

        ytAdminUserService.updateYtAdminUser(ytAdminUser);
        data.put(Constants.TOKEN, token);
        data.put("expire",loginUser.getExpireTime());
        data.put("admin_info",loginResonseBody);
        data.put("nav",loginUser.getNav());
        ajax.put("data",data);


        return ajax;
    }

    @PostMapping("/user/edit")
    public AjaxResult updateUserData(@RequestBody UpdateBody ytAdminUser) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ytAdminUserService.updateYtAdminUser(ytAdminUser);
        Map<String, String> map = new HashMap<>();
        map.put("username",ytAdminUser.getUsername());
        return AjaxResult.success(map);
    }
    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        YtAdminUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        YtAdminUser user1 = loginUser.getUser();
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
