package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DayLog;
import com.ruoyi.system.domain.vo.DayLogMonthVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Repository
public interface DayLogMapper {
    List<DayLog> getDaysList(@Param("fishPond")String fishPond,@Param("begin")Date begin,@Param("end")Date end);

    List<DayLogMonthVo> getMonthsList(@Param("fishPond")String fishPond, @Param("begin")Date begin,@Param("end")Date end);

    DayLogMonthVo getDateSum(@Param("fishPond") String fishPond, @Param("begin")Date begin, @Param("end")Date end);

    void insert(DayLog dayLog);
}
