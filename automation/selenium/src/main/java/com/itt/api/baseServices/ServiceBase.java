package com.itt.api.baseServices;

import com.itt.api.authmanager.APISpecificationManager;
import com.itt.configurations.test.TestConfiguration;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Lazy
@Component
public abstract class ServiceBase {

    @Autowired
    protected TestConfiguration configuration;

    @Autowired
    protected APISpecificationManager apiSpecManager;

    public ValidatableResponse createRecord(Object requestPayload, RequestSpecification spec) {
        RequestSpecification request = given()
                .spec(spec)
                .contentType(ContentType.JSON);

        ValidatableResponse response = request
                .body(requestPayload)
                .log().ifValidationFails(LogDetail.ALL)
                .when().post()
                .then().log().ifValidationFails(LogDetail.ALL);

        return response;
    }

    public ValidatableResponse createRecord(String endPoint, Object requestPayload) {
        RequestSpecification request = given()
                .contentType(ContentType.JSON);

        ValidatableResponse response = request
                .body(requestPayload)
                .log().ifValidationFails(LogDetail.ALL)
                .when().post(endPoint)
                .then().log().ifValidationFails(LogDetail.ALL);

        return response;
    }

    public ValidatableResponse createRecord(String endPoint, Object requestPayload, RequestSpecification spec) {
        RequestSpecification request = given()
                .spec(spec)
                .contentType(ContentType.JSON);

        ValidatableResponse response = request
                .body(requestPayload)
                .log().ifValidationFails(LogDetail.ALL)
                .when().post(endPoint)
                .then().log().ifValidationFails(LogDetail.ALL);

        return response;
    }

    public ValidatableResponse queryRecord(String endPoint, RequestSpecification spec) {
        RequestSpecification request = given()
                .spec(spec)
                .contentType(ContentType.JSON);

        ValidatableResponse response = request
                .log().ifValidationFails(LogDetail.ALL)
                .when().get(endPoint)
                .then().log().ifValidationFails(LogDetail.ALL);

        return response;
    }

    public ValidatableResponse updateRecord(Object requestPayload, RequestSpecification spec) {
        RequestSpecification request = given()
                .spec(spec)
                .contentType(ContentType.JSON);

        ValidatableResponse response = request
                .body(requestPayload)
                .log().ifValidationFails(LogDetail.ALL)
                .when().put()
                .then().log().ifValidationFails(LogDetail.ALL);

        return response;
    }

    public ValidatableResponse updateRecord(String endPoint, Object requestPayload, RequestSpecification spec) {
        RequestSpecification request = given()
                .spec(spec)
                .contentType(ContentType.JSON);

        ValidatableResponse response = request
                .body(requestPayload)
                .log().ifValidationFails(LogDetail.ALL)
                .when().put(endPoint)
                .then().log().ifValidationFails(LogDetail.ALL);

        return response;
    }

}
