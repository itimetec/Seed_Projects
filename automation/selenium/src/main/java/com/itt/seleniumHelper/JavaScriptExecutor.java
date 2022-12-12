package com.itt.seleniumHelper;

import com.itt.constants.TimeOut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.itt.seleniumHelper.SeleniumHelper.*;

public class JavaScriptExecutor {

    private static final Logger LOGGER = LogManager.getLogger(JavaScriptExecutor.class);

    public static Object execute(WebDriver webDriver, String javascript) {
        LOGGER.info("executeJavaScript executing:" + javascript);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Object retVal = js.executeScript(javascript);

        LOGGER.info("executeJavaScript returning:" + retVal);
        return retVal;
    }

    public static Object execute(WebDriver webDriver, String javascript, WebElement element) {
        LOGGER.info("executeJavaScript executing:" + javascript);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Object retVal = js.executeScript(javascript, element);

        LOGGER.info("executeJavaScript returning:" + retVal);
        return retVal;
    }

    public static WebElement getElementById(WebDriver driver, String id) {
        LOGGER.info("Finding Element by Id: {}", id);
        return (WebElement) execute(driver, String.format("return document.getElementById('%s')", id));
    }

    public static String getElementValue(WebDriver driver, WebElement element) {
        String value = (String) execute(driver, "return arguments[0].value", element);
        LOGGER.info("Value obtained from the element is: {}", value);
        return value;
    }

    public static String getElementValue(WebDriver driver, final By locator) {
        WebElement element = findElement(driver, locator);
        String value = (String) execute(driver, "return arguments[0].value", element);
        LOGGER.info("Value obtained from the locator [{}] is: [{}]", locator, value);
        return value;
    }

    public static void forceClick(WebDriver driver, final By locator) {
        LOGGER.info("Performing Java script click on locator [{}]", locator);
        WebElement element = findElement(driver, locator);
        execute(driver, "arguments[0].click()", element);
    }

    public static void scrollIntoView(WebDriver driver, final By locator) {
        WebElement element = findElement(driver, locator);
        LOGGER.info("Scrolling to view element: {}", element);
        execute(driver, "arguments[0].scrollIntoView();", element);
    }

    public static void webKitScrollHorizontal(WebDriver driver, final By locator, int height) {
        WebElement element = findElement(driver, locator);
        LOGGER.info("Scrolling to view element: {}", element);
        execute(driver, String.format("arguments[0].scrollTo(0, %s);", height), element);
    }

    public static void waitUntilTextIsVisible(WebDriver driver, WebElement element) {
        LOGGER.info("Waiting for the element {} text to be visible", element);
        Wait.getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                return !getElementValue(driver, element).isEmpty();
            }
        });
    }

    public static void waitUntilTextIsVisible(WebDriver driver, final By locator) {
        WebElement element = SeleniumHelper.findElement(driver, locator);
        LOGGER.info("Waiting for the element {} text to be visible", element);
        Wait.getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                return !getElementValue(driver, element).isEmpty();
            }
        });
    }

    public static void waitUntilElementTextIsVisible(WebDriver driver, final By locator) {
        LOGGER.info("Waiting for the element with locator {} text to be visible", locator);
        Wait.getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                return !getText(driver, locator).isEmpty();
            }
        });
    }

    public static void setElementValue(WebDriver driver, final By locator, String value) {
        WebElement element = SeleniumHelper.findElement(driver, locator);
        execute(driver, String.format("return arguments[0].value=\"%s\"", value), element);
        LOGGER.info("Element value is set to: {}", value);
    }

    public static void goBack(WebDriver driver) {
        execute(driver, "window.history.go(-1)");
    }

    public static void reloadUntilValueNotToBeNull(WebDriver driver, final By locator, int timeoutInSeconds) {
        LOGGER.info(String.format("Reloading page until locator value not to be null: %s, ", locator));
        Wait.getWebDriverWait(driver, timeoutInSeconds, TimeOut.DEFAULT_POLL_TIMEOUT_MS).until(new ExpectedCondition<Object>() {
            public Object apply(WebDriver input) {
                boolean flag = false;
                if (!getElementValue(driver, locator).isEmpty())
                    flag = true;
                else
                    Window.refresh(driver);
                return flag;
            }
        });
    }

    public static void clickLastOccurrenceWebElement(WebDriver driver, final By locator) {
        LOGGER.info("Performing click on last occurring webElement '{}'", locator);
        Wait.visibilityOfElementLocated(driver, locator);
        WebElement element = getLastOccurrenceWebElement(driver, locator);
        execute(driver, "arguments[0].click()", element);
    }

    public static String getElementText(WebDriver driver, By locator) {
        WebElement element = SeleniumHelper.findElement(driver, locator);
        String text = (String) execute(driver, "return arguments[0].innerText", element);
        LOGGER.info("Obtained element text is: {}", text);
        return text;
    }

    public static void sendLargeTexts(WebDriver driver, By locator, String text) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(locator);
        jsExecutor.executeScript("arguments[1].value=arguments[0]", text, element);
        LOGGER.info("Entered text is: {}", text);
    }
}
