package com.itt.test.api;

import com.itt.api.dataModel.BaseDataModel;
import com.itt.constants.TestGroups;
import com.itt.resources.ui.CreateUser;
import com.itt.test.api.dataProvider.UserDataProvider;
import com.itt.reporting.Tracking;
import com.itt.test.api.base.ProjectAPITestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class UserTest extends ProjectAPITestBase {

    @Tracking(TC = "XXXXX", similarTestID = "XXXXXXX")
    @Test(dataProvider = UserDataProvider.CREATE_USER, dataProviderClass = UserDataProvider.class, groups = {TestGroups.REGRESSION})
    public void verifyCreateUser(BaseDataModel dataModel, CreateUser userPayload) {
        CreateUser createUser = CreateUser.getInstance();

        ValidatableResponse response = userServices.createRecord(userPayload)
                .statusCode(dataModel.getExpectedStatusCode());
        jsonValidation.customizedProperties(response, dataModel.getProperties());
    }
}

