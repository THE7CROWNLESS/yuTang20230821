package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: lyl
 * @Date: 2023/4/26 14:12
 * @Version: v1.0.0
 * @Description: Member类数据库接口
 **/
public interface MemberMapper {
    List<Member> getMemberPage();
}
