package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.LiveUrl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2023-09-08
 */
@Repository
public interface LiveUrlMapper extends BaseMapper<LiveUrl> {

    List<LiveUrl> selectAll();

    int updateNanme(@Param("serialNumber")String serialNumber ,@Param("name") String name);

    String getName(String serialNumber);
}
