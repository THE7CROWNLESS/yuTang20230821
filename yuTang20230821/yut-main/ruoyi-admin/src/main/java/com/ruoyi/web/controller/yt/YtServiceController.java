package com.ruoyi.web.controller.yt;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.yt.YtService;
import com.ruoyi.system.service.yt.IYtServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// 维修服务
@RestController
@RequestMapping("/admin/service")
public class YtServiceController
{
    @Autowired
    private IYtServiceService ytServiceService;

    @GetMapping("/getServiceList")
    public AjaxResult getServiceList(@RequestParam("page")Integer page)
    {
        PageInfo<YtService> ytServicePageInfo = ytServiceService.getServiceList(page);
        return AjaxResult.success("",(int)ytServicePageInfo.getTotal(),ytServicePageInfo.getList());
    }
}
