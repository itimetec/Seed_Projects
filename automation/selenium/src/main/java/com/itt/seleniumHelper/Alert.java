package com.itt.seleniumHelper;

import com.itt.constants.TimeOut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.itt.seleniumHelper.Wait.waitForAlert;

public final class Alert {

    private static final Logger LOGGER = LogManager.getLogger(Alert.class);

    public static boolean isAlertPresent(WebDriver driver) {
        boolean foundAlert = false;
        WebDriverWait webDriverWait = Wait.getWebDriverWait(driver, TimeOut.NANO_TIMEOUT_IN_SEC, TimeOut.NANO_POLL_TIMEOUT_MS);
        try {
            webDriverWait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (TimeoutException eTO) {
            foundAlert = false;
        }
        return foundAlert;
    }

    public static void acceptIfAlert(WebDriver driver) {
        if (isAlertPresent(driver)) {
            driver.switchTo().alert().accept();
            LOGGER.info("Alert Accepted");
        } else {
            LOGGER.warn("No Alert Present");
        }
    }

    public static void acceptAlert(WebDriver driver) {
        waitForAlert(driver);
        LOGGER.info("Accepting Alert!!!");
        driver.switchTo().alert().accept();
    }

    public static String getTextFromPopUp(WebDriver driver) {
        waitForAlert(driver);
        String alertMessage = driver.switchTo().alert().getText();
        LOGGER.info("Alert found with message: {}", alertMessage);
        return alertMessage;
    }

    public static void sendKeys(WebDriver driver, String text) {
        waitForAlert(driver);
        LOGGER.info("Sending Keys to Alert");
        driver.switchTo().alert().sendKeys(text);
    }
}
