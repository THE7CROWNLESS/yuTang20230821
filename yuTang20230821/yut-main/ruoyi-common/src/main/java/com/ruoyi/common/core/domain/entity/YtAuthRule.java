package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author chenshijie
 * @date 2023/4/23 10:00
 */
@Data
public class YtAuthRule {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    /** 规则名称 */
    @Excel(name = "规则名称")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String href;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean checked;

    @Excel(name = "规则名称2")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer type;

    /** 状态 */
    @Excel(name = "状态")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status;

    /** 父级ID */
    @Excel(name = "父级ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pid;

    /** 图标 */
    @Excel(name = "图标")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String icon;

    /** 排序 */
    @Excel(name = "排序")
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long sort;

    /** $column.columnComment */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String condition;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer level;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<YtAuthRule> children;

    public void setChildren(List<YtAuthRule> _children) {
        this.children = _children;
    }

    public List<YtAuthRule> getChildren() {
        return this.children;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setHref(String href)
    {
        this.href = href;
    }

    public String getHref()
    {
        return href;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setPid(Integer pid)
    {
        this.pid = pid;
    }

    public Integer getPid()
    {
        return pid;
    }
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getIcon()
    {
        return icon;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setCondition(String condition)
    {
        this.condition = condition;
    }

    public String getCondition()
    {
        return condition;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getHref())
                .append("title", getTitle())
                .append("type", getType())
                .append("status", getStatus())
                .append("pid", getPid())
                .append("icon", getIcon())
                .append("sort", getSort())
                .append("condition", getCondition())
                .toString();
    }
}
