package com.yyy.spring.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyy.spring.entity.LiveUrl;
import com.yyy.spring.service.ILiveUrlService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2023-09-08
 */
@RestController
@RequestMapping("/live-url")
public class LiveUrlController {

    @Resource
    private ILiveUrlService liveUrlService;

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

    @GetMapping
    public List<LiveUrl> findAll() {
        return liveUrlService.list();
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

}

