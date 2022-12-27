package com.itt.util.apiValidations;

import com.itt.constants.FileType;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.ValidatableResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;

import static io.restassured.internal.matcher.xml.XmlXsdMatcher.matchesXsdInClasspath;

@Lazy
@Component
public class XmlValidation implements SchemaValidation, PropertyValidation {

    @Override
    public void schema(ValidatableResponse validatableResponse, String fileName) {
        if (fileName != null && !fileName.isEmpty())
            validatableResponse.body(matchesXsdInClasspath(SCHEMA_LOCATION + fileName + FileType.XML))
                    .log().ifValidationFails(LogDetail.ALL);
    }

    @Override
    public void properties(ValidatableResponse response, List<HashMap<String, Object>> propertyMapArray) {
        if (propertyMapArray != null) {
            for (HashMap<String, Object> propertyMap : propertyMapArray) {
                Object value = propertyMap.get(VALUE);
                String xpath = (String) propertyMap.get(XML_PATH);
                property(response, xpath, value);
            }
        }
    }

    public static void property(ValidatableResponse response, String xmlPath, Object value) {
        if (value instanceof Integer) {
            Integer val = Integer.parseInt(response.extract().body().xmlPath().get(xmlPath));
            Assert.assertEquals((Integer) value, val);
        } else if (value instanceof String) {
            Assert.assertEquals((String) value, response.extract().body().xmlPath().get(xmlPath));
        }
    }
}
