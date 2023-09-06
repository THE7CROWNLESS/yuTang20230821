package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.YtAuthGroup;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.IYtAuthGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author chenshijie
 * @date 2023/4/23 9:38
 */
@RestController
@RequestMapping("/system/authgroup")
public class YtAuthGroupController extends BaseController {
    @Autowired
    private IYtAuthGroupService ytAuthGroupService;

    /**
     * 查询权限组列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(YtAuthGroup ytAuthGroup)
    {
        startPage();
        List<YtAuthGroup> list = ytAuthGroupService.selectYtAuthGroupList(ytAuthGroup);
        return getDataTable(list);
    }

    /**
     * 导出权限组列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:export')")
    @Log(title = "权限组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YtAuthGroup ytAuthGroup)
    {
        List<YtAuthGroup> list = ytAuthGroupService.selectYtAuthGroupList(ytAuthGroup);
        ExcelUtil<YtAuthGroup> util = new ExcelUtil<YtAuthGroup>(YtAuthGroup.class);
        util.exportExcel(response, list, "权限组数据");
    }

    /**
     * 获取权限组详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(ytAuthGroupService.selectYtAuthGroupById(id));
    }

    /**
     * 新增权限组
     */
    @PreAuthorize("@ss.hasPermi('system:group:add')")
    @Log(title = "权限组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YtAuthGroup ytAuthGroup)
    {
        return toAjax(ytAuthGroupService.insertYtAuthGroup(ytAuthGroup));
    }

    /**
     * 修改权限组
     */
    @PreAuthorize("@ss.hasPermi('system:group:edit')")
    @Log(title = "权限组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YtAuthGroup ytAuthGroup)
    {
        return toAjax(ytAuthGroupService.updateYtAuthGroup(ytAuthGroup));
    }

    /**
     * 删除权限组
     */
    @PreAuthorize("@ss.hasPermi('system:group:remove')")
    @Log(title = "权限组", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(ytAuthGroupService.deleteYtAuthGroupByIds(ids));
    }
}
