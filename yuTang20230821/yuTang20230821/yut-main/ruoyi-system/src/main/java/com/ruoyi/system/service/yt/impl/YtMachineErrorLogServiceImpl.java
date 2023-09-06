package com.ruoyi.system.service.yt.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.yt.YtMachineErrorLog;
import com.ruoyi.system.mapper.yt.YtMachineErrorLogMapper;
import com.ruoyi.system.service.yt.IYtMachineErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class YtMachineErrorLogServiceImpl implements IYtMachineErrorLogService
{
    @Autowired
    private YtMachineErrorLogMapper ytMachineErrorLogMapper;

    @Override
    public PageInfo<YtMachineErrorLog> getYtMachineErrorLogByMachineCode(String machineCode, int page)throws IOException
    {
        PageHelper.startPage(page, 10);
        List<YtMachineErrorLog> ytMachineErrorLogs=ytMachineErrorLogMapper.selectYtMachineErrorLogByMachineCode(machineCode);
        PageInfo<YtMachineErrorLog> pageInfo = new PageInfo<>(ytMachineErrorLogs);
        pageInfo.setPageNum(page);

        return pageInfo;
    }

}
