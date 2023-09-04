package com.ruoyi.common.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenshijie
 * @date 2023/4/26 13:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserBody extends UpdateBody{
    public String group_id;
}
