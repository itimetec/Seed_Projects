package com.itt.pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.itt.seleniumHelper.SeleniumHelper.*;

public class LoginPage extends PageBase {
    public static final Logger LOGGER = LogManager.getLogger(PageBase.class);
    private final By USER_NAME_TEXT_FIELD = By.id("username");
    private final By PASSWORD_TEXT_FIELD = By.id("password");
    private final By LOCATION_LINK = By.id("Outpatient Clinic");
    private final By LOGIN_BUTTON = By.id("loginButton");
    private final By ERROR_MESSAGE_FIELD = By.id("error-message");
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void enterUserName(String userName) {
        clearAndType(driver, USER_NAME_TEXT_FIELD, userName);
    }
    public void enterPassword(String password) {
        clearAndType(driver, PASSWORD_TEXT_FIELD, password);
    }
    public void selectLocation() {
        waitAndClick(driver, LOCATION_LINK);
    }
    public void loginUser() {
        waitAndClick(driver, LOGIN_BUTTON);
    }
    public HomePage login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        selectLocation();
        loginUser();
        return new HomePage(driver);
    }
    public void validateInvalidUser(String username, String password, String errorMessage) {
        LOGGER.info("Logging with username - " + username + "  and password - " + password);
        enterUserName(username);
        enterPassword(password);
        selectLocation();
        loginUser();
        assertion.assertEquals(getText(driver, ERROR_MESSAGE_FIELD), errorMessage, "User is able to Login");
    }
}