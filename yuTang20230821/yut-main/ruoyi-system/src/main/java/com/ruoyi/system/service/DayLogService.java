package com.ruoyi.system.service;

import com.ruoyi.system.domain.DayLog;
import com.ruoyi.system.domain.vo.DayLogMonthVo;

import java.util.Date;
import java.util.List;

public interface DayLogService {

    List<DayLog> getDaysList(String fishPond);

    List<DayLogMonthVo> getMonthsList(String fish_pond);

    List<Integer> getDateSum(String fishPond,Date begin, Date end);
}
