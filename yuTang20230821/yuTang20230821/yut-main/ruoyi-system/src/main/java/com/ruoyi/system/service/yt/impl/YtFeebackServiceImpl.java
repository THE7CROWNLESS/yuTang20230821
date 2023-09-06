package com.ruoyi.system.service.yt.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.yt.YtFeeback;
import com.ruoyi.system.mapper.yt.YtFeebackMapper;
import com.ruoyi.system.service.yt.IYtFeebackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class YtFeebackServiceImpl implements IYtFeebackService
{
    @Autowired
    private YtFeebackMapper ytFeebackMapper;


    @Override
    public PageInfo<YtFeeback> selectYtFeebackList(int page)
    {
        PageHelper.startPage(page, 10);
        List<YtFeeback> ytFeebacks = ytFeebackMapper.selectYtFeebackList();
        PageInfo<YtFeeback> pageInfo = new PageInfo<>(ytFeebacks);
        pageInfo.setPageNum(page);

        return pageInfo;
    }

}
