package com.meiyukai.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Body {
    @JsonProperty(value = "update")
    private String update;

    @JsonProperty(value = "logo")
    private String logo;

    @JsonProperty(value = "updateStr")
    private String updateStr;

    @JsonProperty(value = "dataSize")
    private String dataSize;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "tel")
    private String tel;

    @JsonProperty(value = "showapi_fee_code")
    private String showapi_fee_code;

    @JsonProperty(value = "expSpellName")
    private String expSpellName;

    @JsonProperty(value = "data")
    private List<Info> data;
}
