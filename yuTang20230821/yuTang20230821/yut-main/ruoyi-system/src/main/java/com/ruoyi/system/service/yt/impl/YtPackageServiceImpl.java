package com.ruoyi.system.service.yt.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.yt.YtPackage;
import com.ruoyi.system.domain.yt.vo.YtPackageAddVo;
import com.ruoyi.system.mapper.yt.YtPackageMapper;
import com.ruoyi.system.service.yt.IYtPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class YtPackageServiceImpl implements IYtPackageService
{
    @Autowired
    private YtPackageMapper ytPackageMapper;

    @Override
    public PageInfo<YtPackage> getPackageList(int page)
    {
        PageHelper.startPage(page, 10);
        List<YtPackage> ytPackages = ytPackageMapper.selectYtPackageList();
        PageInfo<YtPackage> pageInfo = new PageInfo<>(ytPackages);
        pageInfo.setPageNum(page);

        return pageInfo;
    }



    @Override
    public YtPackage insertYtPackage(YtPackageAddVo ytPackageAddVo)
    {
        YtPackage ytPackage = new YtPackage();
        ytPackage.setTitle(ytPackageAddVo.getTitle());
        ytPackage.setPay_price(ytPackageAddVo.getPay_price());
        ytPackage.setOrigin_price(ytPackageAddVo.getOrigin_price());
        ytPackage.setValid_day(ytPackageAddVo.getValid_day());

        ytPackage.setType(1);
        ytPackage.setStatus(1);
        ytPackage.setCreate_time(LocalDateTime.now());
        ytPackage.setUpdate_time(LocalDateTime.now());
        Integer id = ytPackageMapper.insertYtPackage(ytPackage);

        return ytPackageMapper.selectYtPackageById(id);
    }


    @Override
    public int editPackage(Integer id,YtPackageAddVo ytPackageAddVo)
    {
        YtPackage ytPackage = new YtPackage();
        ytPackage.setId(id);
        ytPackage.setTitle(ytPackageAddVo.getTitle());
        ytPackage.setValid_day(ytPackageAddVo.getValid_day());
        ytPackage.setOrigin_price(ytPackageAddVo.getOrigin_price());
        ytPackage.setPay_price(ytPackageAddVo.getPay_price());

        ytPackage.setUpdate_time(LocalDateTime.now());
        return ytPackageMapper.updateYtPackage(ytPackage);
    }

    @Override
    public PageInfo<YtPackage> deleteYtPackageById(Integer id)
    {
        ytPackageMapper.deleteYtPackageById(id);

        YtPackage ytPackage = ytPackageMapper.selectYtPackageById(id);
        PageHelper.startPage(1, 10);
        List<YtPackage> ytPackages = new ArrayList<>();
        if(ytPackage!=null){
            ytPackages.add(ytPackage);
        }
        PageInfo<YtPackage> pageInfo = new PageInfo<>(ytPackages);
        pageInfo.setPageNum(1);

        return pageInfo;
    }
}
