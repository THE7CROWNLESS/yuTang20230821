package com.ruoyi.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.LiveUrl;
import com.ruoyi.system.mapper.LiveUrlMapper;
import com.ruoyi.system.service.ILiveUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;

import javax.annotation.Resource;
import java.util.List;

import static com.ruoyi.common.core.domain.AjaxResult.success;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2023-09-08
 */
@RestController
@RequestMapping("/liveurl")
public class LiveUrlController {

    @Resource
    private ILiveUrlService liveUrlService;
    @Autowired
    private LiveUrlMapper liveUrlMapper;

    @PostMapping
    public boolean save(@RequestBody LiveUrl liveUrl){
        return liveUrlService.saveOrUpdate(liveUrl);
    }
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return liveUrlService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return liveUrlService.removeByIds(ids);
    }

    @GetMapping("/{id}")
    public LiveUrl findOne(@PathVariable Integer id) {
        return liveUrlService.getById(id);
    }

    @GetMapping("/page")
    public Page<LiveUrl> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize) {
        QueryWrapper<LiveUrl> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return liveUrlService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @GetMapping
    public List<LiveUrl> findAllUrl() {
        return liveUrlMapper.selectAll();
    }

    @GetMapping("/getName")
    public AjaxResult getName(@RequestParam String serialNumber){
        return success(liveUrlMapper.getName(serialNumber));
    }
    @PostMapping("/setName")
    public AjaxResult setName(@RequestParam String serialNumber ,@RequestParam String name){
        return success(liveUrlMapper.updateNanme(serialNumber ,name));
    }

}

