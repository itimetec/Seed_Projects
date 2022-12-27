package com.itt.base;

import com.itt.testExecution.*;
import com.itt.util.Assertion;
import com.itt.util.ExcelFileHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.*;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static com.itt.constants.ResourceName.TEST_LOGIN_DATA;

public abstract class UITestBase extends TestBase {

    private static final Logger LOGGER = LogManager.getLogger(UITestBase.class);
    @Autowired
    private Environment environment;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        Screenshot.cleanDirectory();
        for (ITestNGMethod method : context.getAllTestMethods()) {
            method.setRetryAnalyzerClass(RerunFailedTests.class);
        }
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        environment.configureAPIGlobalParameters();
    }

    @BeforeMethod(alwaysRun = true)
    public void settingPreCondition(Method method) {
        LOGGER.info("Executing: {}", method.getName());
        environment.setUp(method);
    }

    @AfterMethod(alwaysRun = true, dependsOnMethods = {"tearDown"})
    public void cleanUp(Method method) {
        WebApp.closeChildWindows();
        environment.cleanUp(method);
    }

    @AfterMethod(alwaysRun = true, enabled = false)
    public void cleanUp() {
        WebApp.quitDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result, Method method) {
        if (ITestResult.FAILURE == result.getStatus()) {
            Screenshot.capture(WebApp.getChromeDriver(), method);
        }
    }
}