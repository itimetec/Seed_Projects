package com.itt.util.apiValidations;

import io.restassured.response.ValidatableResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Lazy
@Component
public interface PropertyValidation {

    String ATTRIBUTE = "propertyAttribute";
    String VALUE = "propertyValue";
    String XML_PATH = "xmlPath";

    void properties(ValidatableResponse response, List<HashMap<String, Object>> propertyMapArray);
}
