package com.itt.util.apiValidations;

import io.restassured.response.ValidatableResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;

@Lazy
@Component
public class TextValidation implements PropertyValidation {

    private final String ATTRIBUTE = "propertyAttribute";

    public void validateTextProperty(ValidatableResponse response, String value) {
        Assert.assertTrue(response.extract().asString().contains(value));
    }

    @Override
    public void properties(ValidatableResponse response, List<HashMap<String, Object>> propertyMapArray) {
        if (propertyMapArray != null) {
            for (HashMap<String, Object> propertyMap : propertyMapArray) {
                String validateString = (String) propertyMap.get(ATTRIBUTE);
                validateTextProperty(response, validateString);
            }
        }
    }
}
