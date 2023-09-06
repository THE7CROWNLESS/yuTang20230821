package com.ruoyi.system.service.yt;

import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.yt.YtMachineErrorLog;

import java.io.IOException;
import java.util.List;


public interface IYtMachineErrorLogService 
{
    PageInfo<YtMachineErrorLog> getYtMachineErrorLogByMachineCode(String machineCode,int page)throws IOException;

}
