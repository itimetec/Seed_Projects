package com.itt.api.authmanager;

import com.itt.configurations.test.TestConfiguration;
import com.itt.testExecution.WebApp;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class APISpecificationManager {

    @Autowired
    private TestConfiguration configuration;
    private RequestSpecification apiSpec;

    public RequestSpecification getAPISpec() {
        if (apiSpec == null) {
            apiSpec = new RequestSpecBuilder()
                    .setBaseUri(configuration.getApiBaseUri())
                    .addCookies(WebApp.getCookies()).build();
        }
        return apiSpec;
    }
}
