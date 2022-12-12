package com.itt.testExecution;

import com.itt.reporting.Tracking;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class Screenshot {

    private static final Logger LOGGER = LogManager.getLogger(Screenshot.class);

    public static void capture(WebDriver driver, Method method) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Screenshots/" + method.getAnnotation(Tracking.class).TC()+ '_' + method.getName() + ".png"));
            LOGGER.info("Screenshot taken");
        } catch (Exception e) {
            LOGGER.info("Exception while taking screenshot " + e.getMessage());
        }
    }

    public static void cleanDirectory() {
        File directory = new File("./Screenshots");
        try {
            if (directory.exists() && directory.isDirectory()) {
                FileUtils.cleanDirectory(directory);
            }
        } catch (IOException e) {
            LOGGER.info("Exception while deleting contents of " + directory.getName() + ": " + e.getMessage());
        }
    }

}
