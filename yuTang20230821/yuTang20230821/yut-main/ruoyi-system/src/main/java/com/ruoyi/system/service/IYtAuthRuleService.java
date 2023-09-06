package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.YtAdminUser;
import com.ruoyi.common.core.domain.entity.YtAuthRule;

import java.util.List;
import java.util.Set;

/**
 * @author chenshijie
 * @date 2023/4/23 10:07
 */

public interface IYtAuthRuleService {
    /**
     * 查询规则
     *
     * @param id 规则主键
     * @return 规则
     */
    public YtAuthRule selectYtAuthRuleById(Integer id);

    public Set<YtAuthRule> selectYtAuthRuleByIds(List<Integer> id);
    /**
     * 查询规则列表
     *
     * @param ytAuthRule 规则
     * @return 规则集合
     */
    public List<YtAuthRule> selectYtAuthRuleList(YtAuthRule ytAuthRule);

    /**
     * 新增规则
     *
     * @param ytAuthRule 规则
     * @return 结果
     */
    public int insertYtAuthRule(YtAuthRule ytAuthRule);

    /**
     * 修改规则
     *
     * @param ytAuthRule 规则
     * @return 结果
     */
    public int updateYtAuthRule(YtAuthRule ytAuthRule);

    /**
     * 批量删除规则
     *
     * @param ids 需要删除的规则主键集合
     * @return 结果
     */
    public int deleteYtAuthRuleByIds(Integer[] ids);

    /**
     * 删除规则信息
     *
     * @param id 规则主键
     * @return 结果
     */
    public int deleteYtAuthRuleById(Integer id);

    public Set<YtAuthRule> getPermission(YtAdminUser user);


}
