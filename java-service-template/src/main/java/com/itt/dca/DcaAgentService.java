package com.itt.dca;

import com.itt.dca.config.AppConfiguration;
import com.itt.dca.model.Device;
import com.itt.dca.model.Project;
import com.itt.dca.pharos.PharosConfiguration;
import com.itt.dca.repositories.DeviceRepo;
import com.itt.dca.repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

/**
 * service which interacts with the service.
 */
@Service
public class DcaAgentService {

    /**
     * Spring will initialize pharosConfiguration object.
     */
    @Autowired
    private PharosConfiguration pharosConfiguration;
    /**
     * Spring will initialize AppConfiguration object.
     */
    @Autowired
    private AppConfiguration appConfiguration;
    /**
     * Spring will initialize projectRepo object.
     */
    @Autowired
    private ProjectRepo projectRepo;

    /**
     * Spring will initialize deviceRepo object.
     */
    @Autowired
    private DeviceRepo deviceRepo;
    /**
     * Spring will initialize dcaAgentFactory object.
     */
    @Autowired
    private DcaAgentFactory dcaAgentFactory;

    /**
     * gets the latest or non latest devices.
     * @param projectId id of the project
     * @param latest gets the latest data
     * @throws IOException throws exception if there is run time error
     * @return devices returns list of devices
     */
    public List<Device> getDeviceStatus(final String projectId,
                                        final Boolean latest) throws IOException {
        List<Device> devices = dcaAgentFactory.getDcaAgent(appConfiguration.getTenant()).getDeviceStatus(
                pharosConfiguration.getAuthorization(), projectId);
        return devices;
    }

    /**
     * gets the latest or non latest devices.
     * @param project project info
     * */
    public void registerProject(final Project project) {

    }
}
