package com.itt.test.base;

import com.itt.configurations.test.TestConfiguration;
import com.itt.testExecution.WebApp;
import org.testng.annotations.DataProvider;

import static com.itt.configurations.test.TestConfiguration.getInstance;

public class BaseDataProvider {

    protected TestConfiguration configuration;

    public final static String CONDITIONAL_DATA = "conditionalData";

    public BaseDataProvider() {
        this.configuration = (TestConfiguration) getInstance(TestConfiguration.class);
    }

    @DataProvider(name = CONDITIONAL_DATA)
    public Object[][] getConditionalData() {
        return new Object[][]{
                {new WebApp(configuration), true},
                {new WebApp(configuration), false}
        };
    }
}
