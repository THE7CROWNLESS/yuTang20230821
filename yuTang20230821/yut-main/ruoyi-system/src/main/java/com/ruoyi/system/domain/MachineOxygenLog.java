package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MachineOxygenLog {

    private Integer id;

    private Integer machine_id;

    @JsonProperty("IMEI")
    private String IMEI;

    private Float oxygen;

    private Float degree;

    private String time;

    private String date;

    private String hour;

    private String minute;
}
