package com.itt.configurations.driver;

import com.itt.constants.ResourceName;
import com.itt.util.FileReader;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
public class DriverDataModel {

    private static final Logger LOGGER = LogManager.getLogger(DriverDataModel.class);
    private static DriverDataModel driverDataModel = (DriverDataModel) FileReader.loadDriverFile(ResourceName.DRIVER_CONFIGURATION, DriverDataModel.class);

    public String[] chromeCapabilities;
    public String[] fireFoxCapabilities;
    public String defaultBrowser;

    private DriverDataModel() {}

    public static DriverDataModel getInstance() {
        return driverDataModel;
    }

}
