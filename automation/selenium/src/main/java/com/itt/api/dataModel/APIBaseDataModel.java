package com.itt.api.dataModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public abstract class APIBaseDataModel {

    @JsonProperty("validate")
    private List<HashMap<String, Object>> properties;
    private int expectedStatusCode;
    private String schema;
    private String description;
    private boolean isExecuted;

}
