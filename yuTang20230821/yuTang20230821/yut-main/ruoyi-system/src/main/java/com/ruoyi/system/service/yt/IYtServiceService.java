package com.ruoyi.system.service.yt;


import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.yt.YtService;
import java.util.List;

public interface IYtServiceService 
{
    PageInfo<YtService> getServiceList(int page);
}
