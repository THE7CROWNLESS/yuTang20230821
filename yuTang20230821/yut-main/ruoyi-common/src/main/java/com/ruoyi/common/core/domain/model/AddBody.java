package com.ruoyi.common.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenshijie
 * @date 2023/4/26 10:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBody {
    public String username;

    public String password;

    public String check_password;

    public String group_id;
}
