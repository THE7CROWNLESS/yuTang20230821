package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.DayLog;
import com.ruoyi.system.domain.vo.DayLogMonthVo;
import com.ruoyi.system.mapper.DayLogMapper;
import com.ruoyi.system.service.DayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DayLogServiceImpl implements DayLogService {
    @Autowired
    private DayLogMapper dayLogMapper;

    @Override
    public List<DayLog> getDaysList(String fishPond,Date begin, Date end) {
        return dayLogMapper.getDaysList(fishPond,begin,end);
    }

    @Override
    public List<DayLogMonthVo> getMonthsList(String fish_pond, @RequestParam Date begin, @RequestParam Date end) {
        return dayLogMapper.getMonthsList(fish_pond,begin,end);
    }

    @Override
    public List<Integer> getDateSum(String fishPond,Date begin, Date end) {
        DayLogMonthVo dateSum = dayLogMapper.getDateSum(fishPond, begin, end);
        List<Integer> res = new ArrayList<>();
        res.add(dateSum.getPower());
        res.add(dateSum.getOperation());
        res.add(dateSum.getWarning());
        return res;
    }
}
