package com.yyy.spring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhang
 * @since 2023-09-08
 */
@Getter
@Setter
@TableName("live_url")
@ApiModel(value = "LiveUrl对象", description = "")
public class LiveUrl implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String serialNumber;

    private Integer channelNumber;

    private String hlsUrl;


}
