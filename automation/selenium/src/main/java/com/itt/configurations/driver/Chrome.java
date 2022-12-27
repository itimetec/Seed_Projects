package com.itt.configurations.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome implements ISeleniumWebDriver {

    private DriverDataModel driverDataModel;

    public Chrome() {
        this.driverDataModel = DriverDataModel.getInstance();;
    }

    @Override
    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = (ChromeOptions) setCapabilities();
        options.setExperimentalOption("debuggerAddress","127.0.0.1:9014");
        return new ChromeDriver(options);
    }

    @Override
    public MutableCapabilities setCapabilities() {
        ChromeOptions options = new ChromeOptions();
        String[] arguments = driverDataModel.getChromeCapabilities();
        for (String argument : arguments) {
            options.addArguments(argument);
        }
        return options;
    }
}
