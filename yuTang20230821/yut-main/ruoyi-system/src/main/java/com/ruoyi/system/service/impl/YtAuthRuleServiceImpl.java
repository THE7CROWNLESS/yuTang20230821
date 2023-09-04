package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.YtAdminUser;
import com.ruoyi.common.core.domain.entity.YtAuthGroup;
import com.ruoyi.common.core.domain.entity.YtAuthRule;
import com.ruoyi.system.mapper.YtAuthRuleMapper;
import com.ruoyi.system.service.IYtAuthRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chenshijie
 * @date 2023/4/23 10:07
 */
@Service
public class YtAuthRuleServiceImpl implements IYtAuthRuleService {
    @Autowired
    private YtAuthRuleMapper ytAuthRuleMapper;

    /**
     * 查询规则
     *
     * @param id 规则主键
     * @return 规则
     */
    @Override
    public YtAuthRule selectYtAuthRuleById(Integer id)
    {
        return ytAuthRuleMapper.selectYtAuthRuleById(id);
    }

    /**
     * 查询规则
     *
     * @param ids 规则主键
     * @return 规则
     */
    @Override
    public Set<YtAuthRule> selectYtAuthRuleByIds(List<Integer> ids)
    {
        Set<YtAuthRule> ytAuthRules = ytAuthRuleMapper.selectYtAuthRuleByIds(ids);
        return ytAuthRules;
    }

    /**
     * 查询规则列表
     *
     * @param ytAuthRule 规则
     * @return 规则
     */
    @Override
    public List<YtAuthRule> selectYtAuthRuleList(YtAuthRule ytAuthRule)
    {
        return ytAuthRuleMapper.selectYtAuthRuleList(ytAuthRule);
    }

    /**
     * 新增规则
     *
     * @param ytAuthRule 规则
     * @return 结果
     */
    @Override
    public int insertYtAuthRule(YtAuthRule ytAuthRule)
    {
        if (ytAuthRule.getPid() == null) {
            ytAuthRule.setPid(0);
        }
        if (ytAuthRule.getStatus() == null) {
            throw new RuntimeException("状态不能为空");
        }
        if (ytAuthRule.getTitle() == null) {
            throw new RuntimeException("标题不能为空");
        }
        if (ytAuthRule.getName() == null) {
            throw new RuntimeException("名称不能为空");
        }
        if (ytAuthRule.getSort() == null) {
            throw new RuntimeException("排序不能为空");
        }
        ytAuthRule.setHref(ytAuthRule.getName());



        return ytAuthRuleMapper.insertYtAuthRule(ytAuthRule);
    }

    /**
     * 修改规则
     *
     * @param ytAuthRule 规则
     * @return 结果
     */
    @Override
    public int updateYtAuthRule(YtAuthRule ytAuthRule)
    {
        if (ytAuthRule.getPid() == null) {
            throw new RuntimeException("父级id不能为空");
        }
        if (ytAuthRule.getStatus() == null) {
            throw new RuntimeException("状态不能为空");
        }
        if (ytAuthRule.getTitle() == null) {
            throw new RuntimeException("标题不能为空");
        }
        if (ytAuthRule.getName() == null) {
            throw new RuntimeException("名称不能为空");
        }
        if (ytAuthRule.getSort() == null) {
            throw new RuntimeException("排序不能为空");
        }
        if (ytAuthRule.getId() == null) {
            throw new RuntimeException("id不能为空");
        }
        // mapper中是用href来判断是否修改了name，所以这里也要修改
        ytAuthRule.setHref(ytAuthRule.getName());
        return ytAuthRuleMapper.updateYtAuthRule(ytAuthRule);
    }

    /**
     * 批量删除规则
     *
     * @param ids 需要删除的规则主键
     * @return 结果
     */
    @Override
    public int deleteYtAuthRuleByIds(Integer[] ids)
    {
        return ytAuthRuleMapper.deleteYtAuthRuleByIds(ids);
    }

    /**
     * 删除规则信息
     *
     * @param id 规则主键
     * @return 结果
     */
    @Override
    public int deleteYtAuthRuleById(Integer id)
    {
        return ytAuthRuleMapper.deleteYtAuthRuleById(id);
    }

    @Override
    public Set<YtAuthRule> getPermission(YtAdminUser user) {
        Set<YtAuthRule> perms = new HashSet<>();
        List<YtAuthGroup> roles = user.getRoles();
        if (!roles.isEmpty()) {
            for (YtAuthGroup role : roles) {
                String rules = role.getRules();
                String[] split = rules.split(",");
//                for (String s : split) {
//                    perms.add(this.selectYtAuthRuleById(Integer.valueOf(s)));
//                }

                Integer[] splitInteger = new Integer[split.length];
                for (int i = 0; i < split.length; i++) {
                    splitInteger[i] = Integer.valueOf(split[i]);
                }
                List<Integer> list = Arrays.asList(splitInteger);
                perms.addAll(this.selectYtAuthRuleByIds(list));
            }
        }

        return perms;
    }
}
