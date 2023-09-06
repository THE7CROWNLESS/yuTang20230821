package com.ruoyi.system.service.yt.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.yt.YtService;
import com.ruoyi.system.mapper.yt.YtServiceMapper;
import com.ruoyi.system.service.yt.IYtServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YtServiceServiceImpl implements IYtServiceService
{
    @Autowired
    private YtServiceMapper ytServiceMapper;

    @Override
    public PageInfo<YtService> getServiceList(int page)
    {
        PageHelper.startPage(page, 10);
        List<YtService> ytServices = ytServiceMapper.selectYtServiceList();
        PageInfo<YtService> pageInfo = new PageInfo<>(ytServices);
        pageInfo.setPageNum(page);

        return pageInfo;
    }
}
