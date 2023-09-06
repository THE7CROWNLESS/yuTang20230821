package com.ruoyi.web.controller.yt;

import java.math.BigDecimal;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.yt.YtPackage;
import com.ruoyi.system.domain.yt.vo.YtPackageAddVo;
import com.ruoyi.system.mapper.yt.YtPackageMapper;
import com.ruoyi.system.service.yt.IYtPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/admin/package")
public class YtPackageController
{
    @Autowired
    private IYtPackageService ytPackageService;

    @Autowired
    private YtPackageMapper ytPackageMapper;

    @GetMapping("/getPackageList")
    public AjaxResult getPackageList(@RequestParam("page") Integer page)
    {
        PageInfo<YtPackage> packageList = ytPackageService.getPackageList(page);
        return AjaxResult.success("",(int)packageList.getTotal(),packageList.getList());
    }


    @PostMapping("/addPackage")
    public AjaxResult addPackage(@RequestParam("title")String title, @RequestParam("origin_price")String origin_price,@RequestParam("pay_price")String pay_price,@RequestParam("valid_day")String valid_day)
    {
        YtPackageAddVo ytPackageAddVo = new YtPackageAddVo();
        ytPackageAddVo.setTitle(title);
        ytPackageAddVo.setOrigin_price(new BigDecimal(origin_price));
        ytPackageAddVo.setPay_price(new BigDecimal(pay_price));
        ytPackageAddVo.setValid_day(Integer.parseInt(valid_day));

        YtPackage ytPackageNew = ytPackageService.insertYtPackage(ytPackageAddVo);
        return AjaxResult.success("",ytPackageNew);
    }

    @PostMapping("/editPackage")
    public AjaxResult editPackage(@RequestParam("id")Integer id,@RequestParam("title")String title, @RequestParam("origin_price")String origin_price,@RequestParam("pay_price")String pay_price,@RequestParam("valid_day")String valid_day)
    {

        YtPackageAddVo ytPackageAddVo = new YtPackageAddVo();
        ytPackageAddVo.setTitle(title);
        ytPackageAddVo.setOrigin_price(new BigDecimal(origin_price));
        ytPackageAddVo.setPay_price(new BigDecimal(pay_price));
        ytPackageAddVo.setValid_day(Integer.parseInt(valid_day));

        ytPackageService.editPackage(id,ytPackageAddVo);
        YtPackage ytPackageNew = ytPackageMapper.selectYtPackageById(id);
        return AjaxResult.success("",ytPackageNew);
    }

	@GetMapping("/deletePackage")
    public AjaxResult remove(@RequestParam("id") Integer id)
    {
        PageInfo<YtPackage> ytPackagePageInfo = ytPackageService.deleteYtPackageById(id);
        return AjaxResult.success("",(int)ytPackagePageInfo.getTotal(),ytPackagePageInfo.getList());

    }
}
