package com.ruoyi.system.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.mapper.DeviceThresholdMapper;
import com.ruoyi.system.utils.WarningUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.DeviceThreshold;
import com.ruoyi.system.service.IDeviceThresholdService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【设备阈值】Controller
 *
 * @author ruoyi
 * @date 2023-09-02
 */
@RestController
@RequestMapping("/system/threshold")
public class DeviceThresholdController extends BaseController
{

    @Autowired
    private DeviceThresholdMapper deviceThresholdMapper;
    @Autowired
    private IDeviceThresholdService deviceThresholdService;
    @Autowired
    private WarningUtils warningUtils;

    /**
     * 查询【阈值】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:threshold:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceThreshold deviceThreshold)
    {
        startPage();
        List<DeviceThreshold> list = deviceThresholdService.selectDeviceThresholdList(deviceThreshold);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:threshold:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceThreshold deviceThreshold)
    {
        List<DeviceThreshold> list = deviceThresholdService.selectDeviceThresholdList(deviceThreshold);
        ExcelUtil<DeviceThreshold> util = new ExcelUtil<DeviceThreshold>(DeviceThreshold.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:threshold:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(deviceThresholdService.selectDeviceThresholdById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:threshold:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceThreshold deviceThreshold)
    {
        return toAjax(deviceThresholdService.insertDeviceThreshold(deviceThreshold));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:threshold:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceThreshold deviceThreshold)
    {
        return toAjax(deviceThresholdService.updateDeviceThreshold(deviceThreshold));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:threshold:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(deviceThresholdService.deleteDeviceThresholdByIds(ids));
    }

    /**
     * 获取阈值
     */
    @Log(title = "【获取最新阈值】", businessType = BusinessType.DELETE)
    @GetMapping("/machine_code/{machine_code}")
    public AjaxResult find_by_machine_code(@PathVariable String machine_code){
        DeviceThreshold byMachineCode = deviceThresholdMapper.find_by_machine_code(machine_code);
        // 如果没阈值信息 则插入默认值，并返回
        if (byMachineCode == null) {
            DeviceThreshold deviceThreshold = new DeviceThreshold(null, machine_code, 300, 0, new BigDecimal(500),
                    new BigDecimal(1000), new BigDecimal(0), new BigDecimal(1000), new BigDecimal(0));
            deviceThresholdMapper.insertDeviceThreshold(deviceThreshold);
            return success(deviceThreshold);
        }else {
            return success(deviceThresholdMapper.find_by_machine_code(machine_code));
        }
    }

    /**
     * 更新阈值，需补充日志消息
     */
    @Log(title = "【更新阈值】", businessType = BusinessType.DELETE)
    @PostMapping("/change/")
    public AjaxResult insert_by_machine_code(@RequestBody DeviceThreshold deviceThreshold){
        //System.out.println(deviceThreshold);
        deviceThresholdService.updateDeviceThreshold(deviceThreshold);
        return success(warningUtils.thresholdSetWarning(deviceThreshold.getMachineCode()));
    }
}
