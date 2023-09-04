package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DayRecordsMapper;
import com.ruoyi.system.domain.DayRecords;
import com.ruoyi.system.service.IDayRecordsService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-02
 */
@Service
public class DayRecordsServiceImpl implements IDayRecordsService 
{
    @Autowired
    private DayRecordsMapper dayRecordsMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public DayRecords selectDayRecordsById(Integer id)
    {
        return dayRecordsMapper.selectDayRecordsById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param dayRecords 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<DayRecords> selectDayRecordsList(DayRecords dayRecords)
    {
        return dayRecordsMapper.selectDayRecordsList(dayRecords);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param dayRecords 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertDayRecords(DayRecords dayRecords)
    {
        return dayRecordsMapper.insertDayRecords(dayRecords);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param dayRecords 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateDayRecords(DayRecords dayRecords)
    {
        return dayRecordsMapper.updateDayRecords(dayRecords);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDayRecordsByIds(Integer[] ids)
    {
        return dayRecordsMapper.deleteDayRecordsByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDayRecordsById(Integer id)
    {
        return dayRecordsMapper.deleteDayRecordsById(id);
    }
}
