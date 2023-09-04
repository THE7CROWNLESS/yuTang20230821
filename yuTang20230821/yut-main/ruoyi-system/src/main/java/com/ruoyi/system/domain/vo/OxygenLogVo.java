package com.ruoyi.system.domain.vo;


import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ToString
public class OxygenLogVo {

    private String critical;

    private Map<String, Map> result = new HashMap<>();


}
