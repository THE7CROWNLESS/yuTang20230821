package com.ruoyi.common.core.domain.model;

import lombok.Data;

/**
 * @author chenshijie
 * @date 2023/4/25 10:02
 */
@Data
public class UpdateBody {
    Integer id;
    String username;
    String password;
    String check_password;
}
