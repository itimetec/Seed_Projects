package com.itt.seleniumHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseAction {

    private static final Logger LOGGER = LogManager.getLogger(MouseAction.class);

    public static Actions getActions(WebDriver driver) {
        return new Actions(driver);
    }

    public static void performRightClick(WebDriver driver, final By locator) {
        LOGGER.info("Performing right click on locator: {}", locator);
        Wait.visibilityOfElementLocated(driver, locator);
        getActions(driver).contextClick(driver.findElement(locator)).perform();
    }

    public static void mouseHover(WebDriver driver, final By locator) {
        LOGGER.info("Moving cursor over locator: {}", locator);
        Wait.visibilityOfElementLocated(driver, locator);
        getActions(driver).moveToElement(driver.findElement(locator)).perform();
    }

    public static void click(WebDriver driver, final By locator) {
        LOGGER.info("Performing click on locator: {}", locator);
        Wait.visibilityOfElementLocated(driver, locator);
        getActions(driver).moveToElement(driver.findElement(locator)).click().perform();
    }

    public static void doubleClick(WebDriver driver, WebElement element) {
        LOGGER.info("Performing double click on element: {}", element);
        getActions(driver).doubleClick(element).perform();
    }

    public static void doubleClickChildElement(WebDriver driver, WebElement element, final By childLocator) {
        LOGGER.info("Performing double click on locator: {}", childLocator);
        getActions(driver).doubleClick(element.findElement(childLocator)).perform();
    }
}
