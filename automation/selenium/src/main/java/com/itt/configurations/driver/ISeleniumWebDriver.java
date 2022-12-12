package com.itt.configurations.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public interface ISeleniumWebDriver {

    WebDriver getDriver();
    MutableCapabilities setCapabilities();

}
