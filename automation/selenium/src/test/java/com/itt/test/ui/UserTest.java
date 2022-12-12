package com.itt.test.ui;

import com.itt.constants.Messages;
import com.itt.pageObject.navigations.Pages;
import com.itt.reporting.Tracking;
import com.itt.test.ui.base.WebAppTestBase;
import com.itt.test.ui.dataProvider.UIDataProvider;
import com.itt.testExecution.WebApp;
import com.itt.util.Generate;
import org.testng.annotations.Test;
import com.itt.constants.TestGroups;

import com.itt.pageObject.pages.*;

import static com.itt.test.ui.dataProvider.UIDataProvider.TEST_DATA;

public class UserTest extends WebAppTestBase {

    @Tracking(TC = "XXXXX", similarTestID = "XXXXXXX")
    @Test(dataProvider = TEST_DATA, dataProviderClass = UIDataProvider.class, groups = {TestGroups.REGRESSION, TestGroups.E2E})
    public void validUserLoginTest(WebApp webApp, String userName, String password) {
        LoginPage loginPage = (LoginPage) webApp.openApp(Pages.LOGIN, LoginPage.class);
        HomePage homePage = loginPage.login(userName, password);
        homePage.logOut();
    }

    @Tracking(TC = "XXXXX", similarTestID = "XXXXXXX")
    @Test(dataProvider = TEST_DATA, groups = {TestGroups.REGRESSION, TestGroups.E2E})
    public void invalidUserLoginTest(WebApp webApp) {
        LoginPage loginPage = (LoginPage) webApp.openApp(Pages.LOGIN, LoginPage.class);
        loginPage.validateInvalidUser(Generate.randomString(), Generate.randomString(), Messages.INVALID_USER_ERROR_MSG);
    }
}


