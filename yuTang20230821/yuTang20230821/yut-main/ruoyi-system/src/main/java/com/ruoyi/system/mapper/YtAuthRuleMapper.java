package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.YtAuthRule;

import java.util.List;
import java.util.Set;

public interface YtAuthRuleMapper {

        /**
         * 查询规则
         *
         * @param id 规则主键
         * @return 规则
         */
        public YtAuthRule selectYtAuthRuleById(Integer id);

        /**
         * 查询规则
         *
         * @param ids 规则主键
         * @return 规则
         */
        public Set<YtAuthRule> selectYtAuthRuleByIds(List<Integer> ids);

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
         * 删除规则
         *
         * @param id 规则主键
         * @return 结果
         */
        public int deleteYtAuthRuleById(Integer id);

        /**
         * 批量删除规则
         *
         * @param ids 需要删除的数据主键集合
         * @return 结果
         */
        public int deleteYtAuthRuleByIds(Integer[] ids);
}
