package com.itt.configurations.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class FireFox implements ISeleniumWebDriver {

    private DriverDataModel driverDataModel;

    public FireFox() {
        this.driverDataModel = DriverDataModel.getInstance();;
    }

    @Override
    public WebDriver getDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = (FirefoxOptions) setCapabilities();
        return new FirefoxDriver(options);
    }

    @Override
    public MutableCapabilities setCapabilities() {
        FirefoxOptions options = new FirefoxOptions();
        String[] arguments = driverDataModel.getFireFoxCapabilities();
        for (String argument : arguments) {
            options.addArguments(argument);
        }
        return options;
    }
}
