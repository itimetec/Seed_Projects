package com.itt.seleniumHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.itt.seleniumHelper.SeleniumHelper.findElement;


public class Frames {

    public static void switchTo(WebDriver driver, final String name) {
        Wait.pageToLoad(driver);
        Wait.wait(driver, ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));
    }

    public static void switchTo(WebDriver driver, By locator) {
        driver.switchTo().frame(findElement(driver, locator));
    }

    public static void switchToParentFrame(WebDriver driver) {
        driver.switchTo().parentFrame();
    }

    public static void switchBackToDefault(WebDriver driver){
        driver.switchTo().defaultContent();
    }
}
