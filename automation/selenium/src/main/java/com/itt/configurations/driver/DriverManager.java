package com.itt.configurations.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private static DriverDataModel driverDataModel = DriverDataModel.getInstance();

    public static WebDriver getLocalDriver(ISeleniumWebDriver webDriver) {
       return webDriver.getDriver();
    }

}
