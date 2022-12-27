package com.itt.api.services;

import com.itt.api.baseServices.ServiceBase;
import com.itt.resources.ui.CreateUser;
import io.restassured.response.ValidatableResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class UserServices extends ServiceBase {

    public static final String CREATE_USER_END_POINT = "api/users";

    public ValidatableResponse createRecord(Object requestBody) {
        return createRecord(CREATE_USER_END_POINT, requestBody, apiSpecManager.getAPISpec());
    }

    public CreateUser createUserRecord(String name, String job) {
        CreateUser createUser = CreateUser.getInstance();

        createUser.setJob(job);
        createUser.setName(name);

        return createUser;
    }
}
