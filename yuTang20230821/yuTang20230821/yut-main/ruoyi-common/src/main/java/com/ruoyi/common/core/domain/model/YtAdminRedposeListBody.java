package com.ruoyi.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author chenshijie
 * @date 2023/4/25 11:04
 */
@Data
public class YtAdminRedposeListBody {
    private Long id;

    private String username;

    private int status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String role;

    private int group_id;
}
