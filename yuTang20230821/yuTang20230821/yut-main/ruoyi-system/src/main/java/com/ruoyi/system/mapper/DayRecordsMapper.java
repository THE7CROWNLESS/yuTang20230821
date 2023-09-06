package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DayRecords;
import org.springframework.stereotype.Repository;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2023-09-02
 */
@Repository
public interface DayRecordsMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public DayRecords selectDayRecordsById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param dayRecords 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<DayRecords> selectDayRecordsList(DayRecords dayRecords);

    /**
     * 新增【请填写功能名称】
     *
     * @param dayRecords 【请填写功能名称】
     * @return 结果
     */
    public int insertDayRecords(DayRecords dayRecords);

    /**
     * 修改【请填写功能名称】
     *
     * @param dayRecords 【请填写功能名称】
     * @return 结果
     */
    public int updateDayRecords(DayRecords dayRecords);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteDayRecordsById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDayRecordsByIds(Integer[] ids);
}
