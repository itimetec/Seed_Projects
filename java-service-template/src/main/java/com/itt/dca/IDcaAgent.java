package com.itt.dca;

import com.itt.dca.model.Device;
import java.io.IOException;
import java.util.List;

/**
 * IDcaAgent interface, which is responsible for the actions related to.
 * get device status from various tenants.
 */
public interface IDcaAgent {
    /**
     * Function to fetch the asset pricing data based on the resource.
     * @param dcaSpecificProperty uniquely identifies the data.
     * @param projectId id  of the project
     * @throws IOException if there is any error
     * @return Devices list of devices
     */
    List<Device> getDeviceStatus(String dcaSpecificProperty, String projectId) throws IOException;
}
