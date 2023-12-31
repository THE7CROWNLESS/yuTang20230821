package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Device4g;
import com.ruoyi.system.domain.vo.Device4gNameVo;
import com.ruoyi.system.domain.vo.Device4gVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2023-09-04
 */
@Repository
public interface Device4gMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Device4g selectDevice4gById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @return 【请填写功能名称】集合
     */
    public List<Device4g> selectDevice4gList();

    /**
     * 新增【请填写功能名称】
     *
     * @param device4g 【请填写功能名称】
     * @return 结果
     */
    public int insertDevice4g(Device4g device4g);

    /**
     * 修改【请填写功能名称】
     *
     * @param device4g 【请填写功能名称】
     * @return 结果
     */
    public int updateDevice4g(Device4g device4g);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteDevice4gById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDevice4gByIds(Integer[] ids);


    public Device4g selectDevice4gByImei(String imei);

    public Device4gVo countStatus(String fishPond);

    List<Device4gNameVo> selectStatusList(@Param("status")Integer status, @Param("fishPond")String fish_pond);

    List<Device4gNameVo> selectAllStatusList(@Param("fishPond")String fishPond);

    List<Device4g> pondList();

    Device4g selectByMachineCode(String machineCode);

    String selectMaxIdByFishPond(String 一号养殖场);

    List<Device4g> selectNullFishPond();
}
