package com.ruoyi.web.controller.yt;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.yt.YtMachineErrorLog;
import com.ruoyi.system.service.yt.IYtMachineErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


//  日志
@RestController
@RequestMapping("/admin/report")
public class ReportController
{
    @Autowired
    private IYtMachineErrorLogService ytMachineErrorLogService;


    @GetMapping(value = "/getReportList")
    public AjaxResult getReportList(@RequestParam("machine_id") String machineCode, @RequestParam("page") Integer page)   {

        PageInfo<YtMachineErrorLog> ytMachineErrorLogPageInfo = null;
        try{ytMachineErrorLogPageInfo = ytMachineErrorLogService.getYtMachineErrorLogByMachineCode(machineCode, page);}catch (IOException e) {e.printStackTrace();}
        return AjaxResult.success("",(int)ytMachineErrorLogPageInfo.getTotal(),ytMachineErrorLogPageInfo.getList());
    }

}
