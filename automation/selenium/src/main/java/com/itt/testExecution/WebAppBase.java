package com.itt.testExecution;


import com.itt.configurations.driver.Chrome;
import com.itt.configurations.driver.DriverManager;
import com.itt.seleniumHelper.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class WebAppBase {

    private static final Logger LOGGER = LogManager.getLogger(WebAppBase.class);
    private static WebDriver driver;

    public static WebDriver getChromeDriver() {
        if(driver == null) {
            LOGGER.info("Initializing WebDriver");
            driver = DriverManager.getLocalDriver(new Chrome());
            return driver;
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            LOGGER.info("Quiting Driver");
            driver.quit();
        }
    }

    public static Map<String, String> getCookies() {

        Map<String, String> cookieMap = new HashMap<>();
        Set<Cookie> cookies =  getChromeDriver().manage().getCookies();

        for (Cookie cookie: cookies)
            cookieMap.put(cookie.getName(), cookie.getValue());

        return cookieMap;
    }

    public static void closeChildWindows() {
        Set<String> windowHandles = Window.getWindowHandles(driver);
        for(String handle: windowHandles) {
            if(!handle.equalsIgnoreCase(WebApp.APP_WINDOW)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        try {
            Window.focusWindow(driver, WebApp.APP_WINDOW);
        } catch (NoSuchWindowException e) {
            WebApp.APP_WINDOW = null;
        }
    }
}
