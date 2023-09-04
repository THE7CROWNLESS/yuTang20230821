package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Device4g;
import com.ruoyi.system.service.IDevice4gService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2023-09-04
 */
@RestController
@RequestMapping("/system/4g")
public class Device4gController extends BaseController
{
    @Autowired
    private IDevice4gService device4gService;

    /**
     * 查询【4g模块】列表
     */
    //@PreAuthorize("@ss.hasPermi('system:4g:list')")
    @GetMapping("/list")
    public TableDataInfo list(Device4g device4g)
    {
        startPage();
        List<Device4g> list = device4gService.selectDevice4gList(device4g);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:4g:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Device4g device4g)
    {
        List<Device4g> list = device4gService.selectDevice4gList(device4g);
        ExcelUtil<Device4g> util = new ExcelUtil<Device4g>(Device4g.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:4g:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(device4gService.selectDevice4gById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:4g:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Device4g device4g)
    {
        return toAjax(device4gService.insertDevice4g(device4g));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:4g:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Device4g device4g)
    {
        return toAjax(device4gService.updateDevice4g(device4g));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:4g:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(device4gService.deleteDevice4gByIds(ids));
    }

    /**
     * 设备 状态统计
     * @Param fish_pond
     */
    @GetMapping("/status")
    public AjaxResult countStatus(@RequestParam String fish_pond){
        //logger.info(device4gService.countStatus(fish_pond).toString());
        return success(device4gService.countStatus(fish_pond));
    }

    /**
     * 设备 全部状态下 列表
     * @Param status，fishPond
     */
    @GetMapping("/allStatus/list")
    public AjaxResult selectAllStatusList( @RequestParam String fishPond){
        return success(device4gService.selectAllStatusList(fishPond));
    }

    /**
     * 设备 某状态下 列表
     * @Param status，fishPond
     */
    @GetMapping("/status/list")
    public AjaxResult selectStatusList(@RequestParam Integer status, @RequestParam String fish_pond){
        return success(device4gService.selectStatusList(status,fish_pond));
    }

}
