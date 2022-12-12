package com.itt.util.apiValidations;

import com.itt.constants.FileType;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

@Lazy
@Component
public class JsonValidation implements SchemaValidation, PropertyValidation {

    @Override
    public void schema(ValidatableResponse validatableResponse, String fileName) {
        if (fileName != null && !fileName.isEmpty())
            validatableResponse.body(matchesJsonSchemaInClasspath(SCHEMA_LOCATION + fileName + FileType.JSON))
                    .log().ifValidationFails(LogDetail.ALL);
    }

    @Override
    public void properties(ValidatableResponse response, List<HashMap<String, Object>> propertyMapArray) {
        if (propertyMapArray != null) {
            for (HashMap<String, Object> propertyMap : propertyMapArray) {
                String property = (String) propertyMap.get(ATTRIBUTE);
                Object value = propertyMap.get(VALUE);
                property(response, property, value);
            }
        }
    }

    public void customizedProperties(ValidatableResponse response, List<HashMap<String, Object>> propertyMapArray) {
        if (propertyMapArray != null) {
            for (HashMap<String, Object> propertyMap : propertyMapArray) {
                String property = (String) propertyMap.get(ATTRIBUTE);
                Object value = propertyMap.get(VALUE);
                if (value.equals("notNull"))
                    response.body(property, Matchers.notNullValue());
                else
                    customizedProperty(response, property, value);
            }
        }
    }


    public void property(ValidatableResponse response, String property, Object value) {
        response.body(property, equalTo(value));
    }

    public void customizedProperty(ValidatableResponse response, String property, Object value) {
        response.body(property, containsString(value.toString()));
    }
}
