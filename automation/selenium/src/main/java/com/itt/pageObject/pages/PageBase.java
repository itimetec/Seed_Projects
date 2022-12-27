package com.itt.pageObject.pages;

import com.itt.pageObject.base.DriverPageBase;
import org.apache.logging.log4j.*;

import org.openqa.selenium.WebDriver;

// Use this class to have all common elements thoughout the pages.
public abstract class PageBase extends DriverPageBase {

    public static final Logger LOGGER = LogManager.getLogger(PageBase.class);

    public PageBase(WebDriver driver) {
        super(driver);
    }
}