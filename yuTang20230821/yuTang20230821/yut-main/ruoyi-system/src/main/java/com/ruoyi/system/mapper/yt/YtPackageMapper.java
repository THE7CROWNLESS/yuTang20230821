package com.ruoyi.system.mapper.yt;


import com.ruoyi.system.domain.yt.YtPackage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YtPackageMapper
{


    YtPackage selectYtPackageById(@Param("id") Integer id);

    List<YtPackage> selectYtPackageList();


    Integer insertYtPackage(YtPackage ytPackage);

    int updateYtPackage(YtPackage ytPackage);

    int deleteYtPackageById(@Param("id") Integer id);

}
