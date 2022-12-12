package com.itt.test.ui.base;


import com.itt.base.UITestBase;
import com.itt.util.Assertion;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class WebAppTestBase extends UITestBase {
    @Autowired
    protected Assertion assertion;
}
