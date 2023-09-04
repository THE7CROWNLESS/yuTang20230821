package com.ruoyi.system.service.yt;


import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.yt.YtUserRechangeOrder;
import com.ruoyi.system.domain.yt.vo.YtUserRechangeOrderVo;

import java.util.List;

/**
 * 用户充值Service接口
 * 
 * @author ruoyi
 * @date 2023-04-27
 */
public interface IYtUserRechangeOrderService 
{

    PageInfo<YtUserRechangeOrderVo> selectYtUserRechangeOrderList(int page);

}
