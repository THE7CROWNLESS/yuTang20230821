package com.ruoyi.system.service.yt.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.yt.YtMachineErrorLog;
import com.ruoyi.system.domain.yt.YtUserRechangeOrder;
import com.ruoyi.system.domain.yt.vo.YtUserRechangeOrderVo;
import com.ruoyi.system.mapper.yt.YtUserRechangeOrderMapper;
import com.ruoyi.system.service.yt.IYtUserRechangeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class YtUserRechangeOrderServiceImpl implements IYtUserRechangeOrderService
{
    @Autowired
    private YtUserRechangeOrderMapper ytUserRechangeOrderMapper;


    @Override
    public PageInfo<YtUserRechangeOrderVo> selectYtUserRechangeOrderList(int page)
    {
        PageHelper.startPage(page, 10);
        List<YtUserRechangeOrderVo> ytUserRechangeOrders = ytUserRechangeOrderMapper.selectYtUserRechangeOrderList();
        PageInfo<YtUserRechangeOrderVo> pageInfo = new PageInfo<>(ytUserRechangeOrders);
        pageInfo.setPageNum(page);

        return pageInfo;
    }

}
