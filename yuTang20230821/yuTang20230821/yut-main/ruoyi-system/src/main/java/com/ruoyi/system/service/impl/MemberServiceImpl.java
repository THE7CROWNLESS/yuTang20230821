package com.ruoyi.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.Member;
import com.ruoyi.system.mapper.MemberMapper;
import com.ruoyi.system.service.IMemberService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: lyl
 * @Date: 2023/4/26 15:07
 * @Version: v1.0.0
 * @Description:
 **/
@Service
public class MemberServiceImpl implements IMemberService {

    @Resource
    MemberMapper memberMapper;

    public PageInfo<Member> getMemberService(String page) {
        int pageNum = Integer.parseInt(page);
        PageHelper.startPage(pageNum, 20);
        //mapper接口返回的对象是一个对象数组
        List<Member> list = memberMapper.getMemberPage();
        //把对象数组封装到信息类中
        return new PageInfo<>(list);
    }

}
