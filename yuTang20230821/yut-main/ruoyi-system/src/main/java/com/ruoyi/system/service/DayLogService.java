package com.ruoyi.system.service;

import com.ruoyi.system.domain.DayLog;
import com.ruoyi.system.domain.vo.DayLogMonthVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface DayLogService {

    List<DayLog> getDaysList(String fishPond,Date begin, Date end);

    List<DayLogMonthVo> getMonthsList(String fish_pond, Date begin, Date end);

    List<Integer> getDateSum(String fishPond,Date begin, Date end);
}
