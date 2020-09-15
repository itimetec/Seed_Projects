package com.itt.dca;

import com.itt.dca.model.Device;
import com.itt.dca.model.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

/**
 * This class is responsible for exposing REST APis for dca integration-service.
 */
@RestController
@Slf4j
@RequestMapping("v1")
public class DcaAgentController {

    /**
     * Spring will initialize dcaAgentService object.
     */
    @Autowired
    private DcaAgentService dcaAgentService;

    /**
     * get device-details based on projectID.
     * @param projectId id of the project
     * @param latest gives the latest device details
     * @throws IOException throws the input-output exception
     * @return  Device[]
     */
    @GetMapping("/getDeviceStatus/{projectId}/{latest}")
    Device[] getDeviceStatus(@PathVariable final String projectId,
                             @PathVariable final Boolean latest) throws IOException {
        List<Device> devices = dcaAgentService.getDeviceStatus(projectId, latest);
        return devices.toArray(new Device[]{});
    }
    /**
     * registers the project.
     * @param  project project details
     */
    @PostMapping("/registerProject")
    void registerProject(@RequestBody final Project project) {
        dcaAgentService.registerProject(project);
    }
}
