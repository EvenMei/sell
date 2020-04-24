package com.meiyukai.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Info {
    @JsonProperty(value = "time")
    private String time;

    @JsonProperty(value = "context")
    private String context;

}
