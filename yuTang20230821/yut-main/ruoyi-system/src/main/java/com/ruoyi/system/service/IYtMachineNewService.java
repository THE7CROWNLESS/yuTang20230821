package com.ruoyi.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.YtMachineNew;
import com.ruoyi.system.domain.vo.OxygenAutoVo;

/**
 * 设备Service接口
 *
 * @author ruoyi
 * @date 2023-09-02
 */
public interface IYtMachineNewService {
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
     * 批量删除设备
     *
     * @param ids 需要删除的设备主键集合
     * @return 结果
     */
    public int deleteYtMachineNewByIds(Integer[] ids);

    /**
     * 删除设备信息
     *
     * @param id 设备主键
     * @return 结果
     */
    public int deleteYtMachineNewById(Integer id);

    String updateMqttAerator(YtMachineNew machineData, String num, Integer old, Integer change);

    int updateMqttAeratorSpeed(YtMachineNew ytMachineNew, Integer num, Integer old, Integer speed);

    Map<String, Object> plotCurve(String machineCode, Date day);

    int insertOrUpdateAuto(OxygenAutoVo oxygenAutoVo);

    int computeAutoAeratorSpeed(YtMachineNew ytMachineNew);
}
