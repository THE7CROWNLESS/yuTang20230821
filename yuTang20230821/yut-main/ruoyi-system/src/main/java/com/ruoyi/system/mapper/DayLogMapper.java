package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DayLog;
import com.ruoyi.system.domain.vo.DayLogMonthVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DayLogMapper {
    List<DayLog> getDaysList(String fishPond);

    List<DayLogMonthVo> getMonthsList(String fishPond);

    DayLogMonthVo getDateSum(@Param("fishPond") String fishPond, @Param("begin")Date begin, @Param("end")Date end);

    void insert(DayLog dayLog);
}
