package com.meiyukai.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SFExpress implements Serializable {

    @JsonProperty(value = "showapi_res_error")
    private String showapi_res_error;

    @JsonProperty(value = "showapi_res_id")
    private String showapi_res_id;

    @JsonProperty(value = "showapi_res_code")
    private String showapi_res_code;

    @JsonProperty(value = "showapi_res_body")
    private Body showapi_res_body;

}
