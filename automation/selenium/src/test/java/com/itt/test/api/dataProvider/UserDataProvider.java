package com.itt.test.api.dataProvider;

import com.itt.api.dataModel.BaseDataModel;
import com.itt.api.services.UserServices;
import com.itt.constants.ResourceName;
import com.itt.resources.ui.CreateUser;
import com.itt.util.FileReader;
import org.testng.annotations.DataProvider;
import static com.itt.configurations.test.TestConfiguration.getInstance;

public class UserDataProvider {

    private UserServices userServices;

    public UserDataProvider() {
        this.userServices = (UserServices) getInstance(UserServices.class);
    }

    public static final String CREATE_USER = "createUser";

    @DataProvider(name = CREATE_USER)
    public Object[][] loadTestDataCreateUserRequest() {
        CreateUser createUser = userServices.createUserRecord("jitendra", "Software Engineer");
        Object[][] createUserTestData = (Object[][]) FileReader.loadAPITestData(ResourceName.CREATE_USER_WITH_YAML, BaseDataModel[][].class);
        Object[][] provider = new Object[createUserTestData.length][2];
        for (int i = 0; i < createUserTestData.length; i++) {
            provider[i][0] = createUserTestData[i][0];
            provider[i][1] = createUser;
        }
        return provider;
    }
}