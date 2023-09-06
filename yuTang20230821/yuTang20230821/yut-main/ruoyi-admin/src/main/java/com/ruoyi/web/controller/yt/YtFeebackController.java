package com.ruoyi.web.controller.yt;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.yt.YtFeeback;
import com.ruoyi.system.service.yt.IYtFeebackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 留言
@RestController
@RequestMapping("/admin/feeback")
public class YtFeebackController extends BaseController
{
    @Autowired
    private IYtFeebackService ytFeebackService;

    @GetMapping("/getFeebackList")
    public AjaxResult getFeebackList(@RequestParam("page")Integer page)
    {
        PageInfo<YtFeeback> ytFeebackPageInfo = ytFeebackService.selectYtFeebackList(page);
        return AjaxResult.success("",(int)ytFeebackPageInfo.getTotal(),ytFeebackPageInfo.getList());
    }
}
