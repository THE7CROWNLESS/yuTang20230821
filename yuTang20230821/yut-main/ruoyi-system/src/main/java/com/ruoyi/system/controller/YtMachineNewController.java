package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.DeviceThreshold;
import com.ruoyi.system.domain.YtMachineNew;
import com.ruoyi.system.mapper.DeviceThresholdMapper;
import com.ruoyi.system.mapper.YtMachineNewMapper;
import com.ruoyi.system.service.IYtMachineNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 设备Controller
 *
 * @author ruoyi
 * @date 2023-09-02
 */
@RestController
@RequestMapping("/system/new")
public class YtMachineNewController extends BaseController
{

    @Autowired
    private DeviceThresholdMapper deviceThresholdMapper;
    @Autowired
    private IYtMachineNewService ytMachineNewService;

    @Autowired
    private YtMachineNewMapper ytMachineNewMapper;


    /**
     * 查询设备列表
     */
//    @PreAuthorize("@ss.hasPermi('system:new:list')")
    @GetMapping("/list")
    public TableDataInfo list(YtMachineNew ytMachineNew)
    {
        startPage();
        List<YtMachineNew> list = ytMachineNewService.selectYtMachineNewList(ytMachineNew);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('system:new:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YtMachineNew ytMachineNew)
    {
        List<YtMachineNew> list = ytMachineNewService.selectYtMachineNewList(ytMachineNew);
        ExcelUtil<YtMachineNew> util = new ExcelUtil<YtMachineNew>(YtMachineNew.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:new:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(ytMachineNewService.selectYtMachineNewById(id));
    }

    /**
     * 新增设备
     */
    //@PreAuthorize("@ss.hasPermi('system:new:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YtMachineNew ytMachineNew)
    {
        return toAjax(ytMachineNewService.insertYtMachineNew(ytMachineNew));
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('system:new:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YtMachineNew ytMachineNew)
    {
        return toAjax(ytMachineNewService.updateYtMachineNew(ytMachineNew));
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('system:new:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(ytMachineNewService.deleteYtMachineNewByIds(ids));
    }

    @GetMapping("/device4g")
    public AjaxResult findAllDevice4g() {
//        System.out.println(ytMachineNewMapper.findAllDevice4g());
        return success(ytMachineNewMapper.findAllDevice4g());
    }

    @GetMapping("/machine/{code}")
    public AjaxResult findMachineByMachineCode(@PathVariable String code){
        return success(ytMachineNewMapper.findMachineByMachineCode(code));
    }

    @GetMapping("/warning/{machine_code}")
    public AjaxResult isWarning(@PathVariable String machine_code) {
        // 取最新数据信息
        YtMachineNew ytMachineNew = ytMachineNewMapper.findMachineByMachineCode(machine_code);
        //阈值
        DeviceThreshold deviceThreshold = deviceThresholdMapper.find_by_machine_code(machine_code);

        // 判断 current voltage_max min  temp_max min  oxygen_max min 不符合就设置报警
        Boolean flag = false; // 不报警
        if (ytMachineNew.getCurrent().compareTo(deviceThreshold.getCurrent()) == 1){
            flag = true;
        }
        if (ytMachineNew.getVoltage().compareTo(deviceThreshold.getVoltageMax()) == 1){
            flag = true;
        }
        if (ytMachineNew.getVoltage().compareTo(deviceThreshold.getVoltageMin()) == -1){
            flag = true;
        }
        if (ytMachineNew.getTemperature().compareTo(deviceThreshold.getTemperatureMax()) == 1){
            flag = true;
        }
        if (ytMachineNew.getTemperature().compareTo(deviceThreshold.getTemperatureMin()) == -1){
            flag = true;
        }
        if (ytMachineNew.getOxygen().compareTo(deviceThreshold.getOxygenMax()) == 1){
            flag = true;
        }
        if (ytMachineNew.getOxygen().compareTo(deviceThreshold.getOxygenMin()) == -1){
            flag = true;
        }

        return success(flag);
    }

}
