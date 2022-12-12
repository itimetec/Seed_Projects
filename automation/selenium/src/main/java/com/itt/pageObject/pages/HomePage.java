package com.itt.pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.itt.seleniumHelper.SeleniumHelper.waitAndClick;

public class HomePage extends PageBase {
    private final By LOGOUT_BUTTON = By.xpath("//*[@id='navbarSupportedContent']/ul/li[3]");
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public LoginPage logOut() {
        waitAndClick(driver, LOGOUT_BUTTON);
        return new LoginPage(driver);
    }
}