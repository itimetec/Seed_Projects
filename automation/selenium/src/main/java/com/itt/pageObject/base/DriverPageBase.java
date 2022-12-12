package com.itt.pageObject.base;

import com.itt.util.Assertion;
import org.openqa.selenium.WebDriver;

public class DriverPageBase {
    protected WebDriver driver;
    protected Assertion assertion;

    public DriverPageBase(WebDriver driver) {
        this.driver = driver;
        assertion = new Assertion();
    }
}
