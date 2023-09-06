package com.ruoyi.system.mapper.yt;

import java.util.List;
import com.ruoyi.system.domain.yt.YtMachineErrorLog;
import org.apache.ibatis.annotations.Param;


public interface YtMachineErrorLogMapper 
{

    List<YtMachineErrorLog> selectYtMachineErrorLogByMachineCode(@Param("machineCode") String machineCode);
}
