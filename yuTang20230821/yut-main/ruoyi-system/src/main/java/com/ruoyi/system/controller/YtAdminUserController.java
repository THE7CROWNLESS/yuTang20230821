package com.ruoyi.system.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.YAuthGroups;
import com.ruoyi.common.core.domain.entity.YtAdminUser;
import com.ruoyi.common.core.domain.entity.YtAuthGroup;
import com.ruoyi.common.core.domain.entity.YtAuthRule;
import com.ruoyi.common.core.domain.model.AddBody;
import com.ruoyi.common.core.domain.model.UpdateUserBody;
import com.ruoyi.common.core.domain.model.YtAdminRedposeListBody;
import com.ruoyi.common.core.domain.model.YtAdminUserBody;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.IYtAdminUserService;
import com.ruoyi.system.service.IYtAuthGroupService;
import com.ruoyi.system.service.IYtAuthRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理员用户Controller
 * 
 * @author ruoyi
 * @date 2023-04-21
 */
@RestController
@RequestMapping("/admin")
public class YtAdminUserController extends BaseController
{
    @Autowired
    private IYtAdminUserService ytAdminUserService;

    @Autowired
    private IYtAuthGroupService ytAuthGroupService;

    @Autowired
    private IYtAuthRuleService ytAuthRuleService;
//
    /**
     * 查询后台管理员用户列表
     */

//    @GetMapping("/user/list")
//    public TableDataInfo list(YtAdminUser ytAdminUser)
//    {
//        startPage();
//        List<YtAdminUser> list = ytAdminUserService.selectYtAdminUserList(ytAdminUser);
//        return getDataTable(list);
//    }

    @GetMapping("/user/userListJson")
    public AjaxResult list2(@RequestParam(value = "page",required = false,defaultValue = "1") String page)
    {
//        startPage(Integer.valueOf(page));
        YtAdminUser ytAdminUser = new YtAdminUser();
        List<YtAdminUser> list = ytAdminUserService.selectYtAdminUserList(ytAdminUser);
        startPage(Integer.valueOf(page));


        List<YtAdminRedposeListBody> res = ytAdminUserService.selectYtBody(ytAdminUser);

        TableDataInfo dataTable = getDataTable(res);
        return AjaxResult.success(dataTable.getRows()).put("count",list.size());
    }

    @GetMapping("/user/detail")
    public AjaxResult getUserById(@RequestParam(value = "id",required = true) String id)
    {
        Map<String, Object> object = new HashMap<>();
        YtAdminRedposeListBody body = ytAdminUserService.selectYtBodyById(Long.valueOf(id));
        object.put("data",body);
        YtAuthGroup ytAuthGroup = ytAuthGroupService.selectYtAuthGroupById(body.getGroup_id());
        String title = ytAuthGroup.getTitle();
        Map<String, Object> auth_group = new HashMap<>();
        auth_group.put("id",body.getGroup_id());
        auth_group.put("title",title);
        object.put("auth_group",auth_group);
        return AjaxResult.success(object);
    }

    @PostMapping("/user/addUser")
    public AjaxResult add(@RequestBody AddBody addBody)
    {
        YtAdminUser ytAdminUser = ytAdminUserService.insertYtAdminUser(addBody);
        YtAdminUser res = new YtAdminUser();
        res.setCreate_time(ytAdminUser.getCreate_time());
        res.setId(ytAdminUser.getId());
        res.setStatus(ytAdminUser.getStatus());
        res.setUsername(ytAdminUser.getUsername());
        res.setPassword(ytAdminUser.getPassword());
        return AjaxResult.success("ok",res);
    }

    // 后台管理员修改
    @PostMapping("/user/editUser")
    public AjaxResult edit(@RequestBody UpdateUserBody body)
    {
        int i = ytAdminUserService.updateYtAdminUser(body);
        List<YtAdminUserBody> list = ytAdminUserService.selectYtAdminUserList(new YtAdminUserBody());


        return AjaxResult.success("ok",list).put("count",list.size());
    }

    // 后台管理员删除
    @GetMapping("/user/deleteUser")
    public AjaxResult del(@RequestParam(value = "id",required = true) String id)
    {
        int i = ytAdminUserService.deleteYtAdminUserById(Long.valueOf(id));
        return AjaxResult.success("删除成功","").put("count",0);
    }

    // 获取管理组 todo增加body
    @GetMapping("/user/getGroup")
    public AjaxResult getGroup()
    {
        List<YtAuthGroup> list = ytAuthGroupService.selectYtAuthGroupList(new YtAuthGroup());

        return AjaxResult.success("ok",list);
    }

    // 权限列表
    @GetMapping("/user/getTableData")
    public AjaxResult getTableData()
    {
        List<YtAuthRule> list = ytAuthRuleService.selectYtAuthRuleList(new YtAuthRule());
        for (YtAuthRule ytAuthRule : list) {
            ytAuthRule.setName(ytAuthRule.getHref());
            ytAuthRule.setHref(null);
        }
        return AjaxResult.success("ok",list);
    }
    // 角色修改(授权）
    @PostMapping("/user/editRole")
    public AjaxResult editGroup(@RequestBody YAuthGroups ytAuthGroups)
    {
        YtAuthGroup ytAuthGroup1 = new YtAuthGroup();
        ytAuthGroup1.setId(ytAuthGroups.getId());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ytAuthGroups.getRules().size(); i++) {
            builder.append(ytAuthGroups.getRules().get(i));
            if (i != ytAuthGroups.getRules().size()-1) {
                builder.append(",");
            }
        }
        ytAuthGroup1.setRules(builder.toString());
        int i = ytAuthGroupService.updateYtAuthGroup(ytAuthGroup1);

//        int i = ytAuthGroupService.updateYtAuthGroup(ytAuthGroup);
        return AjaxResult.success("ok",ytAuthGroups);
    }
    // 角色列表数据
    @PostMapping("/user/getJson")
    public AjaxResult getJson(@RequestBody Map id)
    {

        int getId =(int)id.get("id");
        List<YtAuthRule> ytAuthRules = ytAuthRuleService.selectYtAuthRuleList(new YtAuthRule());

        Map<Integer, Object> map = new HashMap<>();
        for (int i = 0; i < ytAuthRules.size(); i++) {
            YtAuthRule ytAuthRule = ytAuthRules.get(i);
            map.put(ytAuthRule.getId(),ytAuthRule);
        }



        YtAuthGroup ytAuthGroup2 = ytAuthGroupService.selectYtAuthGroupById(getId);
        String rules1 = ytAuthGroup2.getRules();
        if (rules1 != null) {
            String[] split1 = rules1.split(",");

            for (int i = 0; i < split1.length; i++) {
                int s = Integer.valueOf(split1[i]);
                if (map.containsKey(s)) {
                    YtAuthRule o = (YtAuthRule)map.get(s);
                    o.setChecked(true);
                    map.put(s,o);
                }
            }
        }

        List<YtAuthRule> list = new ArrayList<>();
        for (Map.Entry<Integer, Object> entry : map.entrySet()) {
            list.add((YtAuthRule)entry.getValue());
        }


        return AjaxResult.success("ok",list);
    }

    // 权限列表
//    @GetMapping("/user/getGroup")
//    public AjaxResult getTableData2()
//    {
//        List<YtAuthRule> list = ytAuthRuleService.selectYtAuthRuleList(new YtAuthRule());
//        for (YtAuthRule ytAuthRule : list) {
//            ytAuthRule.setName(ytAuthRule.getHref());
//            ytAuthRule.setHref(null);
//        }
//        return AjaxResult.success("ok",list);
//    }

    // 权限添加
    @PostMapping("/user/addRule")
    public AjaxResult addRule(@RequestBody YtAuthRule ytAuthRule)
    {

        int i = ytAuthRuleService.insertYtAuthRule(ytAuthRule);
        ytAuthRule.setLevel(1);
        ytAuthRule.setHref(null);

        return AjaxResult.success("ok",ytAuthRule);
    }

    // 权限修改
    // todo测试需要先做详情
    @PostMapping("/user/editRule")
    public AjaxResult editRule(@RequestBody YtAuthRule ytAuthRule)
    {
        int i = ytAuthRuleService.updateYtAuthRule(ytAuthRule);
        return AjaxResult.success(ytAuthRule.getHref(),"").put("count",0);
    }

    // 权限详情
    @GetMapping("/user/ruleDetail")
    public AjaxResult ruleDetai(@RequestParam(value = "id",required = true) String id)
    {
        YtAuthRule ytAuthRule = ytAuthRuleService.selectYtAuthRuleById(Integer.valueOf(id));
        ytAuthRule.setName(ytAuthRule.getHref());
        Map<String, Object> map = new HashMap<>();
        map.put("data",ytAuthRule);
        return AjaxResult.success("ok",map);

    }

    // 权限删除
    @GetMapping("/user/delRule")
    public AjaxResult deleteRule(@RequestParam(value = "id",required = true) String id)
    {
        YtAuthRule ytAuthRule = ytAuthRuleService.selectYtAuthRuleById(Integer.valueOf(id));
        int i = ytAuthRuleService.deleteYtAuthRuleById(Integer.valueOf(id));
        return AjaxResult.success("ok",ytAuthRule);
    }

    // 角色列表
    @GetMapping("/user/roleListJson")
    public AjaxResult getRole()
    {

        List<YtAuthGroup> list = ytAuthGroupService.selectYtAuthGroupList(new YtAuthGroup());
        return AjaxResult.success("",list).put("count",list.size());
    }

    // 角色添加
    @PostMapping("/user/addRole")
    public AjaxResult addRole(@RequestBody YtAuthGroup ytAuthGroup)
    {
        ytAuthGroup.setRules("1");
        int i = ytAuthGroupService.insertYtAuthGroup(ytAuthGroup);
        List<YtAuthGroup> list = ytAuthGroupService.selectYtAuthGroupList(new YtAuthGroup());
        return AjaxResult.success("",list).put("count",list.size());
    }

    // 角色删除
    @PostMapping("/user/delRole")
    public AjaxResult delRole(@RequestBody Map data)
    {
        int id = (int)data.get("id");
        int i = ytAuthGroupService.deleteYtAuthGroupById(Integer.valueOf(id));
        YtAuthRule ytAuthRule = new YtAuthRule();
        List<YtAuthRule> list = ytAuthRuleService.selectYtAuthRuleList(ytAuthRule);

        return AjaxResult.success("ok",list).put("count",list.size());
    }


//    @PostMapping("/user/editRole2")
//    public AjaxResult editRole(@RequestBody YtAuthGroup ytAuthGroup)
//    {
////        int i = ytAuthGroupService.updateYtAuthGroup(ytAuthGroup);
//        return AjaxResult.success("ok",ytAuthGroup);
//    }
//
//    /**
//     * 导出后台管理员用户列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:user:export')")
//    @Log(title = "后台管理员用户", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, YtAdminUser ytAdminUser)
//    {
//        List<YtAdminUser> list = ytAdminUserService.selectYtAdminUserList(ytAdminUser);
//        ExcelUtil<YtAdminUser> util = new ExcelUtil<YtAdminUser>(YtAdminUser.class);
//        util.exportExcel(response, list, "后台管理员用户数据");
//    }
//
//    /**
//     * 获取后台管理员用户详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:user:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Integer id)
//    {
//        return success(ytAdminUserService.selectYtAdminUserById(id));
//    }
//
//    /**
//     * 新增后台管理员用户
//     */
//    @PreAuthorize("@ss.hasPermi('system:user:add')")
//    @Log(title = "后台管理员用户", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody YtAdminUser ytAdminUser)
//    {
//        return toAjax(ytAdminUserService.insertYtAdminUser(ytAdminUser));
//    }
//
//    /**
//     * 修改后台管理员用户
//     */
//    @PreAuthorize("@ss.hasPermi('system:user:edit')")
//    @Log(title = "后台管理员用户", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody YtAdminUser ytAdminUser)
//    {
//        return toAjax(ytAdminUserService.updateYtAdminUser(ytAdminUser));
//    }
//
//    /**
//     * 删除后台管理员用户
//     */
//    @PreAuthorize("@ss.hasPermi('system:user:remove')")
//    @Log(title = "后台管理员用户", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Integer[] ids)
//    {
//        return toAjax(ytAdminUserService.deleteYtAdminUserByIds(ids));
//    }

//    @GetMapping("/aare")
//    public AjaxResult list2() {
//        return AjaxResult.success();
//    }
}
