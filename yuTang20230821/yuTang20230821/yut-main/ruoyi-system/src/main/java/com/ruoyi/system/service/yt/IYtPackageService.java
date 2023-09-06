package com.ruoyi.system.service.yt;


import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.yt.YtPackage;
import com.ruoyi.system.domain.yt.vo.YtPackageAddVo;

public interface IYtPackageService
{

    PageInfo<YtPackage> getPackageList(int page);


    YtPackage insertYtPackage(YtPackageAddVo ytPackageAddVo);


    int editPackage(Integer id,YtPackageAddVo ytPackageAddVo);

    PageInfo<YtPackage> deleteYtPackageById(Integer id);
}
