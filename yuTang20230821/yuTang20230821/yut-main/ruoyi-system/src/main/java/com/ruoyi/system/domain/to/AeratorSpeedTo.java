package com.ruoyi.system.domain.to;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@Data
@ToString
@Repository
public class AeratorSpeedTo {

        private String machineCode;
        private Integer num;
        private Integer speed;
}
