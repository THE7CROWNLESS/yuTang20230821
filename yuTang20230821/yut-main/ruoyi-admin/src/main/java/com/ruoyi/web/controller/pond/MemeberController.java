package com.ruoyi.web.controller.pond;

/*
* 会员列表控制
* @author lyl
*
* */

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Member;
import com.ruoyi.system.service.IMemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/member")
public class MemeberController {

    @Resource
    private IMemberService memberService;

    /**
     *
     * @param page
     * @return 所有的用户信息
     */
    @GetMapping("/getMemberList")
    public AjaxResult getMemberList(@RequestParam("page") String page){
        PageInfo<Member> list = memberService.getMemberService(page);
        return AjaxResult.success("", (int)list.getTotal(), list.getList());
    }


}
