package com.itt.seleniumHelper;

import com.itt.constants.TimeOut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.itt.seleniumHelper.SeleniumHelper.findElement;
import static com.itt.seleniumHelper.SeleniumHelper.waitAndClick;
import static org.awaitility.Awaitility.await;

public class Wait {

    private static final Logger LOGGER = LogManager.getLogger(Wait.class);

    public static void wait(WebDriver driver, ExpectedCondition<?> ec) {
        getWebDriverWait(driver).until(ec);
    }

    public static void wait(WebDriver driver, int timeOutInSeconds, int pollTimeInMilliSeconds, ExpectedCondition<?> ec) {
        getWebDriverWait(driver, timeOutInSeconds, pollTimeInMilliSeconds).until(ec);
    }

    private static ExpectedCondition<Boolean> pageReady() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                boolean result = false;
                String readyState = "";
                try {
                    readyState = (String) JavaScriptExecutor.execute(driver, "return document.readyState;");
                } catch (WebDriverException e) {
                    LOGGER.info("Caught " + e);
                }

                if ("complete".equals(readyState)) {
                    result = true;
                }
                return result;
            }
        };
    }

    public static void reloadUntilPresenceOfElement(WebDriver driver, final By TABNameLocator, final By locator) {
        LOGGER.info(String.format("Reloading page until the presence of element : %s, ", locator));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                boolean flag = SeleniumHelper.isElementPresent(driver, locator);
                if (!flag)
                    Window.refresh(driver);
                Alert.acceptIfAlert(driver);
                waitAndClick(driver, TABNameLocator);
                return flag;
            }
        });
    }

    public static void reloadUntilPresenceOfElement(WebDriver driver, final By locator, int timeoutInSeconds) {
        LOGGER.info(String.format("Reloading page until the presence of element : %s till: %s, ", locator, timeoutInSeconds));
        getWebDriverWait(driver, timeoutInSeconds, TimeOut.DEFAULT_POLL_TIMEOUT_MS).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                boolean flag = SeleniumHelper.isElementPresent(driver, locator);
                if (!flag)
                    Window.refresh(driver);
                return flag;
            }
        });
    }

    public static WebDriverWait getWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, TimeOut.DEFAULT_NAVIGATION_TIMEOUT_SEC, TimeOut.DEFAULT_POLL_TIMEOUT_MS);
    }

    public static WebDriverWait getWebDriverWait(WebDriver driver, int timeOutInSeconds, int pollTimeInMilliSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds, pollTimeInMilliSeconds);
    }

    public static void pageToLoad(WebDriver driver) {
        getWebDriverWait(driver).until(pageReady());
    }

    public static WebElement elementToBeClickable(WebDriver driver, final By locator) {
        LOGGER.info(String.format("Waiting for locator: %s, to be clickable ...", locator));
        return getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement elementToBeClickableAfterCustomTimeout(WebDriver driver, final By locator, int timeoutInSeconds) {
        LOGGER.info(String.format("Waiting for locator: %s, to be clickable ...", locator));
        return getWebDriverWait(driver, timeoutInSeconds, TimeOut.DEFAULT_POLL_TIMEOUT_MS).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement visibilityOfElementLocated(WebDriver driver, final By locator) {
        LOGGER.info(String.format("Waiting for locator: %s, visibility ...", locator));
        return getWebDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement visibilityOfElementLocated(WebDriver driver, WebElement element) {
        LOGGER.info(String.format("Waiting for WebElement: %s, visibility ...", element));
        return getWebDriverWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement presenceOfElementLocated(WebDriver driver, final By locator) {
        LOGGER.info(String.format("Waiting for presence of locator:  %s ...", locator));
        return getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static Boolean refreshOfElementLocated(WebDriver driver, final WebElement locator) {
        LOGGER.info(String.format("Waiting for presence of locator:  %s ...", locator));
        return getWebDriverWait(driver).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(locator)));
    }

    public static void textToBePresentInElementLocated(WebDriver driver, final By locator, final String text) {
        LOGGER.info(String.format("Waiting for the text to br present in the locator: %s ...", locator));
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static void invisibilityOfElementWithText(WebDriver driver, final By locator, final String text) {
        LOGGER.info(String.format("Waiting for the text to be invisible in the locator %s ...", locator));
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
    }

    public static void invisibilityOfElement(WebDriver driver, final By locator) {
        LOGGER.info(String.format("Waiting for the text to be invisible in the locator %s ...", locator));
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void attributeToBe(WebDriver driver, final By locator, final String attribute, final String value) {
        LOGGER.info(String.format("Waiting for locator: %s, attribute: %s, value to be: %s ...", locator, attribute, value));
        getWebDriverWait(driver).until(ExpectedConditions.attributeToBe(locator, attribute, value));
    }

    public static void attributeToBe(WebDriver driver, WebElement element, final String attribute, final String value) {
        LOGGER.info(String.format("Waiting for locator: %s, attribute: %s, value to be: %s ...", element, attribute, value));
        getWebDriverWait(driver).until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public static void waitForAlert(WebDriver driver) {
        LOGGER.info(String.format("Waiting for Alert"));
        getWebDriverWait(driver).until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForElementToBeSelected(WebDriver driver, final By locator, final String value) {
        LOGGER.info(String.format("Waiting for locator: %s, value to be selected: %s ...", locator, value));
        getWebDriverWait(driver).until(ExpectedConditions.textToBe(locator, value));
    }

    public static void attributeContains(WebDriver driver, final By locator, final String attribute, final String value) {
        LOGGER.info(String.format("Waiting for locator: %s, attribute: %s, to contain value: %s ...", locator, attribute, value));
        getWebDriverWait(driver).until(ExpectedConditions.attributeContains(locator, attribute, value));
    }

    public static boolean isElementPresent(WebDriver driver, final By locator) {
        LOGGER.info(String.format("Checking for the presence of locator %s", locator));
        try {
            getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
                public Object apply(WebDriver input) {
                    try {
                        findElement(driver, locator).isDisplayed();
                    } catch (StaleElementReferenceException | NoSuchElementException e) {
                        return false;
                    }
                    return true;
                }
            });
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public static void attributeNotToBeNull(WebDriver driver, final By locator, final String attribute) {
        LOGGER.info(String.format("Waiting for locator: %s, attribute: %s, not to be null", locator, attribute));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                return !(SeleniumHelper.getAttribute(driver, locator, attribute).isEmpty());
            }
        });
    }

    public static void attributeNotToBeNull(WebDriver driver, int timeOutInSeconds, int pollTimeInMilliSeconds, final By locator, final String attribute) {
        LOGGER.info(String.format("Waiting for locator: %s, attribute: %s, not to be null", locator, attribute));
        getWebDriverWait(driver, timeOutInSeconds, pollTimeInMilliSeconds).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                return !(SeleniumHelper.getAttribute(driver, locator, attribute).isEmpty());
            }
        });
    }

    public static void verifyAttributeIsNotNull(WebDriver driver, final By locator, final String attribute) {
        LOGGER.info(String.format("Waiting for locator: %s, attribute: %s, not to be null", locator, attribute));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                return !(SeleniumHelper.getAttribute(driver, locator, attribute) == null);
            }
        });
    }

    public static void reloadUntilPresenceOfElement(WebDriver driver, final By locator) {
        LOGGER.info(String.format("Reloading page until the presence of element : %s, ", locator));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                boolean flag = SeleniumHelper.isElementPresent(driver, locator);
                if (!flag)
                    Window.refresh(driver);
                return flag;
            }
        });
    }

    public static void reloadUntilVisibilityOfElement(WebDriver driver, final By locator) {
        LOGGER.info(String.format("Reloading page until the visibility of element : %s, ", locator));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                boolean isVisible = SeleniumHelper.isElementVisible(driver, locator);
                if (!isVisible)
                    Window.refresh(driver);
                return isVisible;
            }
        });
    }

    public static void waitForSlushBucketToLoad(WebDriver driver, Select dropdown) {
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                String firstOption = dropdown.getOptions().get(0).getText();
                if (firstOption.equals("--None--") || firstOption.equals("Loading") || firstOption.isEmpty())
                    return false;
                return true;
            }
        });
    }

    public static void waitForSlushBucketToPopulate(WebDriver driver, Select dropdown) {
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                List<WebElement> firstOption = dropdown.getOptions();
                if (firstOption.size() == 0 || firstOption.get(0).getText().equals("--None--") || firstOption.get(0).getText().equals("Loading") || firstOption.get(0).getText().isEmpty())
                    return false;
                return true;
            }
        });
    }

    public static void reloadUntilTextTobe(WebDriver driver, final By locator, String expectedText) {
        LOGGER.info(String.format("Reloading page until the presence of element : %s, ", locator));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                boolean flag = false;
                if (SeleniumHelper.findElement(driver, locator).getText().equals(expectedText))
                    flag = true;
                else
                    Window.refresh(driver);
                return flag;
            }
        });
    }

    public static void windowHandleCountToBe(WebDriver driver, int limit) {
        LOGGER.info(String.format("Waiting for window count to be : %s, ", limit));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                return Window.getWindowHandles(driver).size() == limit;
            }
        });
    }

    public static void tillDropDownContains(WebDriver driver, final By locator, String option) {
        LOGGER.info(String.format("Waiting for the dropdown to load "));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                return DropDown.doesContains(driver, locator, option);
            }
        });
    }

    public static void tillDropDownSizeIsGreaterThan(WebDriver driver, final By locator, int size) {
        LOGGER.info(String.format("Waiting for the dropdown to have size greater than : %s", size));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                return DropDown.getDropDownListSize(driver, locator) > size;
            }
        });
    }


    public static void reloadTillDropDownVisibleTextToBe(WebDriver driver, final By locator, String visibleText) {
        LOGGER.info(String.format("Waiting for the dropdown value to be %s", visibleText));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                if (DropDown.getSelectedValue(driver, locator).equals(visibleText)) {
                    return true;
                } else {
                    Window.refresh(driver);
                    return false;
                }
            }
        });
    }

    public static void reloadUntilElementValueTobe(WebDriver driver, final By locator, String expectedText) {
        LOGGER.info("Reloading page until locator [{}] value to be [{}]", locator, expectedText);
        Wait.getWebDriverWait(driver, TimeOut.MAX_TIMEOUT_IN_SEC, TimeOut.DEFAULT_POLL_TIMEOUT_MS).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                if (JavaScriptExecutor.getElementValue(driver, findElement(driver, locator)).equals(expectedText))
                    return true;
                else
                    Window.refresh(driver);
                return false;
            }
        });
    }

    public static void reloadUntilDropdownValueTobe(WebDriver driver, final By locator, String expectedText) {
        LOGGER.info("Reloading page until dropdown locator [{}] value to be [{}]", locator, expectedText);
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            public Object apply(WebDriver input) {
                if (JavaScriptExecutor.getElementValue(driver, findElement(driver, locator)).equals(expectedText))
                    return true;
                else
                    Window.refresh(driver);
                return false;
            }
        });
    }

    public static void reloadUntilTextContains(WebDriver driver, final By locator, String expectedText) {
        LOGGER.info(String.format("Reloading page until the presence of element : %s, ", locator));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            public Object apply(WebDriver input) {
                if (SeleniumHelper.findElement(driver, locator).getText().contains(expectedText))
                    return true;
                else
                    Window.refresh(driver);
                return false;
            }
        });
    }

    public static void reloadWithTimeIntervalUntilTextContains(WebDriver driver, final By locator, String expectedText, int timeoutInSeconds) {
        LOGGER.info(String.format("Reloading page until the presence of element : %s, ", locator));
        getWebDriverWait(driver, timeoutInSeconds, TimeOut.DEFAULT_POLL_TIMEOUT_MS).until(new ExpectedCondition<Object>() {
            public Object apply(WebDriver input) {
                if (SeleniumHelper.findElement(driver, locator).getText().contains(expectedText))
                    return true;
                else
                    Window.refresh(driver);
                return false;
            }
        });
    }

    public static void reloadUntilDropdownValueTobe(WebDriver driver, final By locator, String expectedText, int timeoutInSeconds) {
        LOGGER.info("Reloading page until dropdown locator [{}] value to be [{}]", locator, expectedText);
        getWebDriverWait(driver, timeoutInSeconds, TimeOut.DEFAULT_POLL_TIMEOUT_MS).until(new ExpectedCondition<Object>() {
            public Object apply(WebDriver input) {
                if (DropDown.getSelectedValue(driver, locator).equals(expectedText))
                    return true;
                else
                    Window.refresh(driver);
                return false;
            }
        });
    }

    public static void reloadUntilCheckBoxDisabled(WebDriver driver, final By locator) {
        LOGGER.info(String.format("Reloading page until the presence of element : %s", locator));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                boolean flag = false;
                if (SeleniumHelper.isSelected(driver, locator)) {
                    Window.refresh(driver);
                } else {
                    flag = true;
                }
                return flag;
            }
        });
    }

    public static void reloadUntilAttributeNotNull(WebDriver driver, final By locator, final String attribute) {
        LOGGER.info(String.format("Reloading page until locator: %s, attribute: %s, not to be null", locator, attribute));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                boolean flag = false;
                if ((SeleniumHelper.getAttribute(driver, locator, attribute).isEmpty())) {
                    Window.refresh(driver);
                } else
                    flag = true;
                return flag;
            }
        });
    }

    public static void reloadUntilValueSelected(WebDriver driver, final By locator, final String attribute, final String text) {
        LOGGER.info(String.format("Reloading page until  attribute: %s, is: %s ", attribute, text));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                boolean flag = false;
                if ((!SeleniumHelper.getAttribute(driver, locator, attribute).equalsIgnoreCase(text))) {
                    Window.refresh(driver);
                } else
                    flag = true;
                return flag;
            }
        });
    }

    public static void waitTillFileExists(String path, long time, TimeUnit unit) {
        await().atMost(time, unit)
                .ignoreExceptions()
                .until(() -> Paths.get(path).toFile().exists());
    }

    public static void refreshUntilRelatedListElementAppears(WebDriver driver, final By locator, String relatedListHamburgerMenu, final By refreshList) {
        LOGGER.info(String.format("Reloading page until the presence of element : %s, ", locator));
        getWebDriverWait(driver).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                boolean flag = SeleniumHelper.isElementPresent(driver, locator);
                if (!flag) {
                    waitAndClick(driver, By.xpath(relatedListHamburgerMenu));
                    waitAndClick(driver, refreshList);
                }
                return flag;
            }
        });
    }

    public static void refreshUntilRelatedListElementAppears(WebDriver driver, final By locator, String relatedListHamburgerMenu, final By refreshList, int timeoutInSeconds) {
        LOGGER.info(String.format("Reloading page until the presence of element : %s, ", locator));
        getWebDriverWait(driver, timeoutInSeconds, TimeOut.DEFAULT_POLL_TIMEOUT_MS).until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver input) {
                boolean flag = SeleniumHelper.isElementPresent(driver, locator);
                if (!flag) {
                    waitAndClick(driver, By.xpath(relatedListHamburgerMenu));
                    waitAndClick(driver, refreshList);
                }
                return flag;
            }
        });
    }

}