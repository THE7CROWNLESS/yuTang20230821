package com.ruoyi.web.controller.yt;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.yt.YtUserRechangeOrder;
import com.ruoyi.system.domain.yt.vo.YtUserRechangeOrderVo;
import com.ruoyi.system.service.yt.IYtUserRechangeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 订单管理
@RestController
@RequestMapping("/admin/order")
public class YtUserRechangeOrderController
{
    @Autowired
    private IYtUserRechangeOrderService ytUserRechangeOrderService;


    @GetMapping("/getOrderList")
    public AjaxResult list(@RequestParam("page")Integer page)
    {
        PageInfo<YtUserRechangeOrderVo> ytUserRechangeOrderPageInfo = ytUserRechangeOrderService.selectYtUserRechangeOrderList(page);
        return AjaxResult.success("",(int)ytUserRechangeOrderPageInfo.getTotal(),ytUserRechangeOrderPageInfo.getList());
    }

}
