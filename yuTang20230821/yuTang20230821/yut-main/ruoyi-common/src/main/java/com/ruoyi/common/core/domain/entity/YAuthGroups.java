package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.List;

/**
 * @author chenshijie
 * @date 2023/5/5 10:13
 */
@Data
public class YAuthGroups {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */

    private String title;

    /** $column.columnComment */

    private Integer status;

    /** 权限规则ID */

    private List<Integer> rules;

    /** $column.columnComment */

    private String remarks;
}
