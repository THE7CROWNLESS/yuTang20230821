package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.YtAuthRule;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.IYtAuthRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author chenshijie
 * @date 2023/4/23 10:08
 */
@RestController
@RequestMapping("/admin")
public class YtAuthRuleController extends BaseController {
    @Autowired
    private IYtAuthRuleService ytAuthRuleService;

//    /**
//     * 查询规则列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:rule:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(YtAuthRule ytAuthRule)
//    {
//        startPage();
//        List<YtAuthRule> list = ytAuthRuleService.selectYtAuthRuleList(ytAuthRule);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出规则列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:rule:export')")
//    @Log(title = "规则", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, YtAuthRule ytAuthRule)
//    {
//        List<YtAuthRule> list = ytAuthRuleService.selectYtAuthRuleList(ytAuthRule);
//        ExcelUtil<YtAuthRule> util = new ExcelUtil<YtAuthRule>(YtAuthRule.class);
//        util.exportExcel(response, list, "规则数据");
//    }
//
//    /**
//     * 获取规则详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:rule:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Integer id)
//    {
//        return success(ytAuthRuleService.selectYtAuthRuleById(id));
//    }
//
//    /**
//     * 新增规则
//     */
//    @PreAuthorize("@ss.hasPermi('system:rule:add')")
//    @Log(title = "规则", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody YtAuthRule ytAuthRule)
//    {
//        return toAjax(ytAuthRuleService.insertYtAuthRule(ytAuthRule));
//    }
//
//    /**
//     * 修改规则
//     */
//    @PreAuthorize("@ss.hasPermi('system:rule:edit')")
//    @Log(title = "规则", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody YtAuthRule ytAuthRule)
//    {
//        return toAjax(ytAuthRuleService.updateYtAuthRule(ytAuthRule));
//    }
//
//    /**
//     * 删除规则
//     */
//    @PreAuthorize("@ss.hasPermi('system:rule:remove')")
//    @Log(title = "规则", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Integer[] ids)
//    {
//        return toAjax(ytAuthRuleService.deleteYtAuthRuleByIds(ids));
//    }


}
