package com.itt.test.ui.dataProvider;

import com.itt.configurations.test.TestConfiguration;
import static com.itt.configurations.test.TestConfiguration.getInstance;
import static com.itt.constants.ResourceName.TEST_LOGIN_DATA;

import com.itt.test.base.BaseDataProvider;
import com.itt.testExecution.WebApp;
import com.itt.util.ExcelFileHandler;
import org.testng.annotations.DataProvider;

public class UIDataProvider extends BaseDataProvider {

    protected TestConfiguration configuration;

    public UIDataProvider() {
        this.configuration = (TestConfiguration) getInstance(TestConfiguration.class);
    }

    public final static String TEST_DATA = "testData";

    @DataProvider(name = TEST_DATA)
    public Object[][] tempTestData() {
        String[][] testData = ExcelFileHandler.getExcelDataIn2DArray(TEST_LOGIN_DATA.toString(), "loginSheet");
        return new Object[][]{
                {new WebApp(configuration), testData[0][0], testData[0][1]},
        };
    }
}
