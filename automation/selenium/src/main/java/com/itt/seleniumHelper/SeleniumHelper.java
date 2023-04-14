package com.itt.seleniumHelper;

import com.google.common.collect.Iterables;
import com.itt.constants.TimeOut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SeleniumHelper {

    private static final Logger LOGGER = LogManager.getLogger(SeleniumHelper.class);

    public static WebElement findElement(WebDriver driver, final By locator) {
        return driver.findElement(locator);
    }

    public static void click(WebDriver driver, final By locator) {
        LOGGER.info("Performing click action ... ");
        findElement(driver, locator).click();
    }

    public static void click(WebElement element) {
        LOGGER.info("Performing click action ... ");
        element.click();
    }

    public static void waitAndClick(WebDriver driver, final By locator) {
        WebElement element = Wait.elementToBeClickable(driver, locator);
        LOGGER.info("Performing click action ... ");
        element.click();
    }

    public static void waitClickAndEnter(WebDriver driver, final By locator) {
        WebElement element = Wait.elementToBeClickable(driver, locator);
        LOGGER.info("Performing click action ... ");
        element.click();
        findElement(driver, locator).sendKeys(Keys.ENTER);
    }

    public static void waitAndClickWithCustomTimeout(WebDriver driver, final By locator, int timeoutInSeconds) {
        WebElement element = Wait.elementToBeClickableAfterCustomTimeout(driver, locator, timeoutInSeconds);
        LOGGER.info("Performing click action ... ");
        element.click();
    }

    public static void type(WebDriver driver, final By locator, String keyword) {
        LOGGER.info(String.format("Typing %s ...", keyword));
        findElement(driver, locator).sendKeys(keyword);
    }

    public static void clearAndType(WebDriver driver, final By locator, String keyword) {
        findElement(driver, locator).clear();
        LOGGER.info(String.format("Typing %s ...", keyword));
        findElement(driver, locator).sendKeys(keyword);
    }

    public static void clearAndEnter(WebDriver driver, final By locator) {
        findElement(driver, locator).clear();
        findElement(driver, locator).sendKeys(Keys.ENTER);
    }

    public static void clearTypeAndEnter(WebDriver driver, final By locator, String keyword) {
        findElement(driver, locator).clear();
        LOGGER.info(String.format("Typing %s ...", keyword));
        findElement(driver, locator).sendKeys(keyword, Keys.ENTER);
    }

    public static void clearTypeAndEnter(final WebElement webElement, String keyword) {
        webElement.clear();
        LOGGER.info(String.format("Typing %s ...", keyword));
        webElement.sendKeys(keyword, Keys.ENTER);
    }

    public static  void waitAndClear(WebDriver driver, final By locator){
        Wait.visibilityOfElementLocated(driver, locator);
        findElement(driver, locator).clear();
        LOGGER.info("Cleared field...");
    }

    public static void waitClearTypeAndEnter(WebDriver driver, final By locator, String keyword) {
        Wait.elementToBeClickable(driver, locator);
        findElement(driver, locator).clear();
        findElement(driver, locator).sendKeys(keyword, Keys.ENTER);
        LOGGER.info(String.format("wait and Cleared field and typed and entered %s ...", keyword));
    }

    public static void waitClearAndType(WebDriver driver, final By locator, String keyword) {
        Wait.elementToBeClickable(driver, locator);
        findElement(driver, locator).clear();
        findElement(driver, locator).sendKeys(keyword);
        LOGGER.info(String.format("Cleared field and typed  %s ...", keyword));
    }

    public static void waitAndType(WebDriver driver, final By locator, String keyword) {
        Wait.elementToBeClickable(driver, locator);
        LOGGER.info(String.format("Typing %s ...", keyword));
        findElement(driver, locator).sendKeys(keyword);
    }

    public static void typeAndEnter(WebDriver driver, final By locator, String keyword) {
        LOGGER.info(String.format("Typing %s ...", keyword));
        findElement(driver, locator).sendKeys(keyword, Keys.ENTER);
    }

    public static void waitTypeAndEnter(WebDriver driver, final By locator, String keyword) {
        Wait.elementToBeClickable(driver, locator);
        LOGGER.info(String.format("Typing %s ...", keyword));
        findElement(driver, locator).sendKeys(keyword, Keys.ENTER);
    }

    public static String getText(WebDriver driver, final By locator) {
        WebElement element = Wait.visibilityOfElementLocated(driver, locator);
        return element.getText();
    }

    public static String getText(WebElement webElement) {
        String text = webElement.getText();
        LOGGER.info("Obtained text from element is: {} ", text);
        return text;
    }

    public static String getAttribute(WebDriver driver, final By locator, String attribute) {
        WebElement element = Wait.visibilityOfElementLocated(driver, locator);
        String value = element.getAttribute(attribute);
        LOGGER.info("Attribute value obtained from element is: {} ", value);
        return value;
    }

    public static String getAttribute(WebDriver driver, WebElement element, String attribute) {
        Wait.visibilityOfElementLocated(driver, element);
        String value = element.getAttribute(attribute);
        LOGGER.info("Attribute value obtained from element is: {} ", value);
        return value;
    }

    public static String getAttributeOnPageLoad(WebDriver driver, final By locator, String attribute) {
        String value = findElement(driver, locator).getAttribute(attribute);
        LOGGER.info("Attribute value obtained from element is: {} ", value);
        return value;
    }

    public static boolean isFieldMarkedAsReadOnly(WebDriver driver, final By locator) {
        LOGGER.info(" Checking The Read Only Attribute For The Following Locator: {} ", locator);
        try {
            WebElement element = findElement(driver, locator);
            if (element.getAttribute("readonly") != null && element.getAttribute("readonly").equals("true"))
                return true;
            if (element.getAttribute("aria-readonly") != null && element.getAttribute("aria-readonly").equals("true"))
                return true;
            if (element.getAttribute("disabled") != null && element.getAttribute("disabled").equals("disabled"))
                return true;
            else
                return element.getAttribute("class") != null && element.getAttribute("class").contains("disabled");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementPresent(WebDriver driver, final By locator) {
        LOGGER.info("Verifying the presence of elements with locator: {} ...", locator);
        return findElements(driver, locator).size() > 0;
    }

    public static boolean isElementVisible(WebDriver driver, final By locator) {
        LOGGER.info("Verifying the visibility of elements with locator: {} ...", locator);
        try {
            return findElement(driver, locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public static boolean isChildElementVisible(WebElement element, final By childLocator) {
        LOGGER.info("Verifying the visibility of child elements with locator: {} ...", element);
        try {
            return findChildElement(element, childLocator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public static boolean isElementVisible(WebElement element) {
        LOGGER.info("Checking the visibility of element...");
        return element.isDisplayed();
    }

    public static boolean isElementEnabled(WebDriver driver, final By locator) {
        boolean state = findElement(driver, locator).isEnabled();
        LOGGER.info("Locator:[{}] enabled: {} ...", locator, state);
        return state;
    }

    public static int getElementCount(WebDriver driver, final By locator) {
        int count = findElements(driver, locator).size();
        LOGGER.info("Total elements found with locator: {}, is: {}", locator, count);
        return count;
    }

    public static List<WebElement> findElements(WebDriver driver, final By locator) {
        LOGGER.info("Finding elements with locator: {} ...", locator);
        return driver.findElements(locator);
    }

    public static WebElement findChildElement(WebElement element, final By childLocator) {
        LOGGER.info("Finding the child element with locator: {} ...", childLocator);
        return element.findElement(childLocator);
    }

    public static boolean isClickable(WebDriver driver, WebElement locator) {
        LOGGER.info("Verifying clickability of element with locator: {} ...", locator);
        try {
            Wait.getWebDriverWait(driver, TimeOut.MIN_TIMEOUT_IN_SEC, TimeOut.DEFAULT_POLL_TIMEOUT_MS)
                    .until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (ElementClickInterceptedException e) {
            return false;
        }
    }

    public static boolean isSelected(WebDriver driver, final By locator) {
        LOGGER.info("Verifying the checkbox with locator '{}' is checked or not", locator);
        return findElement(driver, locator).isSelected();
    }

    public static void enableCheckbox(WebDriver driver, final By locator) {
        LOGGER.info("Enabling the checkbox with locator '{}'", locator);
        if (!isSelected(driver, locator)) {
            JavaScriptExecutor.forceClick(driver, locator);
        }
    }

    public static void disableCheckbox(WebDriver driver, final By locator) {
        LOGGER.info("Disabling the checkbox with locator '{}'", locator);
        if (isSelected(driver, locator)) {
            JavaScriptExecutor.forceClick(driver, locator);
        }
    }

    public static WebElement getLastOccurrenceWebElement(WebDriver driver, final By locator) {
        LOGGER.info("Fetching the last occurrence of the webElement '{}'", locator);
        List<WebElement> webElements = findElements(driver, locator);
        return Iterables.getLast(webElements);
    }

    public static void clickLastOccurrenceWebElement(WebDriver driver, final By locator) {
        LOGGER.info("Performing click on last occurring webElement '{}'", locator);
        Wait.visibilityOfElementLocated(driver, locator);
        WebElement element = getLastOccurrenceWebElement(driver, locator);
        click(element);
    }

    public static String getCssValue(WebDriver driver, final By locator, String cssValue) {
        WebElement element = Wait.visibilityOfElementLocated(driver, locator);
        String value = element.getCssValue(cssValue);
        LOGGER.info("Css value obtained from element is: {} ", value);
        return value;
    }

    public static Point getElementLocation(WebDriver driver, final By locator) {
        WebElement element = Wait.visibilityOfElementLocated(driver, locator);
        Point value = element.getLocation();
        LOGGER.info("location value obtained from element is: {} ", value);
        return value;
    }

    public static boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

}
