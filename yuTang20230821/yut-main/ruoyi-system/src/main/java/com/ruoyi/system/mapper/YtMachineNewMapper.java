package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Device4g;
import com.ruoyi.system.domain.YtMachineNew;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 设备Mapper接口
 *
 * @author ruoyi
 * @date 2023-09-02
 */
@Repository
public interface YtMachineNewMapper
{
    /**
     * 查询设备
     *
     * @param id 设备主键
     * @return 设备
     */
    public YtMachineNew selectYtMachineNewById(Integer id);

    /**
     * 查询设备列表
     *
     * @param ytMachineNew 设备
     * @return 设备集合
     */
    public List<YtMachineNew> selectYtMachineNewList(YtMachineNew ytMachineNew);

    /**
     * 新增设备
     *
     * @param ytMachineNew 设备
     * @return 结果
     */
    public int insertYtMachineNew(YtMachineNew ytMachineNew);

    /**
     * 修改设备
     *
     * @param ytMachineNew 设备
     * @return 结果
     */
    public int updateYtMachineNew(YtMachineNew ytMachineNew);

    /**
     * 删除设备
     *
     * @param id 设备主键
     * @return 结果
     */
    public int deleteYtMachineNewById(Integer id);

    /**
     * 批量删除设备
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYtMachineNewByIds(Integer[] ids);


    public List<Device4g> findAllDevice4g();

    public YtMachineNew findMachineByMachineCode(String code);

    public String findMaxMachineCode();

    public void insertNew(YtMachineNew ytMachineNew);

    int insertAeratorChange(YtMachineNew machineData);

    List<Integer> plotCurve(@Param("machineCode")String machineCode, @Param("day") Date day);

}
