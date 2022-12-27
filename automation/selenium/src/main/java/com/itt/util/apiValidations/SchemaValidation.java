package com.itt.util.apiValidations;

import io.restassured.response.ValidatableResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public interface SchemaValidation {
    String SCHEMA_LOCATION = "Schemas/";
    void schema(ValidatableResponse validatableResponse, String schemaLocation);
}
