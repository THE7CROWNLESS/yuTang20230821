package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.YtMachineNew;
import com.ruoyi.system.domain.to.AeratorTo;
import com.ruoyi.system.mapper.YtMachineNewMapper;
import com.ruoyi.system.service.IYtMachineNewService;
import com.ruoyi.system.utils.WarningUtils;
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
public class YtMachineNewController extends BaseController {

    @Autowired
    private WarningUtils warningUtils;
    @Autowired
    private IYtMachineNewService ytMachineNewService;
    @Autowired
    private YtMachineNewMapper ytMachineNewMapper;

    /**
     * 查询设备列表
     */
//    @PreAuthorize("@ss.hasPermi('system:new:list')")
    @GetMapping("/list")
    public TableDataInfo list(YtMachineNew ytMachineNew) {
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
    public void export(HttpServletResponse response, YtMachineNew ytMachineNew) {
        List<YtMachineNew> list = ytMachineNewService.selectYtMachineNewList(ytMachineNew);
        ExcelUtil<YtMachineNew> util = new ExcelUtil<YtMachineNew>(YtMachineNew.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:new:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return success(ytMachineNewService.selectYtMachineNewById(id));
    }

    /**
     * 新增设备
     */
    //@PreAuthorize("@ss.hasPermi('system:new:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YtMachineNew ytMachineNew) {
        return toAjax(ytMachineNewService.insertYtMachineNew(ytMachineNew));
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('system:new:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YtMachineNew ytMachineNew) {
        return toAjax(ytMachineNewService.updateYtMachineNew(ytMachineNew));
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('system:new:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(ytMachineNewService.deleteYtMachineNewByIds(ids));
    }


    @GetMapping("/device4g")
    public AjaxResult findAllDevice4g() {
//        System.out.println(ytMachineNewMapper.findAllDevice4g());
        return success(ytMachineNewMapper.findAllDevice4g());
    }

    // 最新 设备详情
    @GetMapping("/machine/{code}")
    public AjaxResult findMachineByMachineCode(@PathVariable String code) {
        return success(ytMachineNewMapper.findMachineByMachineCode(code));
    }

    // 根据 设备编码 设备报警状态
    @GetMapping("/warning/{machine_code}")
    public AjaxResult isWarning(@PathVariable String machine_code) {
        return success(warningUtils.thresholdWarning(machine_code));
    }

    // 根据参数 设置 设备 的开关机状态，发布mqtt消息，记录日志
    @PostMapping("/aerator/switch")
    public AjaxResult switchAerator(@RequestBody AeratorTo aeratorTo) {
        String machine_code = aeratorTo.getMachineCode();
        Integer old = aeratorTo.getOld();
        Integer num = aeratorTo.getNum();
        Integer change = aeratorTo.getChange();

        // 查数据表 电机状态
        YtMachineNew machineData = ytMachineNewMapper.findMachineByMachineCode(machine_code);
//        int old = 0;
        // 判断是否 更改了 状态
        if (old != change) {
            // 选择 电机序号
            if (num == 1) {
                machineData.setAerator1Status(change);
            } else if (num == 2) {
                machineData.setAerator2Status(change);
            } else if (num == 3) {
                machineData.setAerator3Status(change);
            } else if (num == 4) {
                machineData.setAerator4Status(change);
            }
            return success(ytMachineNewService.updateMqttAerator(machineData,num.toString(), old, change));
        } else {
            return success();
        }
    }
}
