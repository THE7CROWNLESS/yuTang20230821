package com.ruoyi.system.service;

import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.Member;

import java.util.List;

/**
 * @Author: lyl
 * @Date: 2023/4/26 15:05
 * @Version: v1.0.0
 * @Description: TODO
 **/
public interface IMemberService {
    PageInfo<Member> getMemberService(String page);
}
