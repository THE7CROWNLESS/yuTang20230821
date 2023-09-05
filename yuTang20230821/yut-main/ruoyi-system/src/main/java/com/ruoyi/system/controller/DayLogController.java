package com.ruoyi.system.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.DayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/system/log")
public class DayLogController extends BaseController {

    @Autowired
    private DayLogService dayLogService;

    @GetMapping("/days")
    public AjaxResult getDaysList(@RequestParam String fish_pond) {
        return success(dayLogService.getDaysList(fish_pond));
    }

    @GetMapping("/months")
    public AjaxResult getMonthsList(@RequestParam String fish_pond) {
        return success(dayLogService.getMonthsList(fish_pond));
    }

    @GetMapping("/date")
    public AjaxResult getDateSum(@RequestParam String fish_pond,@RequestParam Date begin, @RequestParam Date end) {
        return success(dayLogService.getDateSum(fish_pond,begin, end));
    }
}
