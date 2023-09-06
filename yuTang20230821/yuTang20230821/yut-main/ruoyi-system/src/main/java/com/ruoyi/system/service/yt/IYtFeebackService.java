package com.ruoyi.system.service.yt;



import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.yt.YtFeeback;


public interface IYtFeebackService 
{

    PageInfo<YtFeeback> selectYtFeebackList(int page);


}
