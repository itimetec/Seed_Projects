package com.itt.testExecution;

import com.itt.configurations.test.TestConfiguration;
import com.itt.pageObject.navigations.Pages;
import com.itt.seleniumHelper.*;
import com.itt.seleniumHelper.Alert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.lang.reflect.*;

public class WebApp extends WebAppBase {

    private static final Logger LOGGER = LogManager.getLogger(WebApp.class);

    private TestConfiguration config;
    public static String APP_WINDOW;

    public WebApp(TestConfiguration config) {
        this.config = config;
    }

    public Object openApp(Pages pageName, Class type) {
        return getPage(pageName, type);
    }

    public Object getPage(Pages pageName, Class type) {
        WebDriver driver = getChromeDriver();
        Object object = null;

        if (APP_WINDOW == null) {
            APP_WINDOW = driver.getWindowHandle();
        } else {
            Window.focusWindow(driver, WebApp.APP_WINDOW);
        }

        try {
            Class<?> aClass = Class.forName(type.getName());
            Constructor<?> constructor = aClass.getConstructor(WebDriver.class);

            driver.get(config.getUiBaseUri() + pageName.toString());
            Alert.acceptIfAlert(driver);
            object = constructor.newInstance(driver);

        } catch (Exception e) {
            Assert.fail("Test case terminated because of: " + e.getMessage());
        }
        return object;
    }
}
