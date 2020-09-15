package com.itt.dca.pharos;

import com.itt.dca.IDcaAgent;
import com.itt.dca.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * it implements the dcaAgent.
 */
@Component
public class PharosDcaAgent implements IDcaAgent {
    /**
     * Spring intializes the pharosConfiguration.
     */
    @Autowired
    private PharosConfiguration pharosConfiguration;

    /**
     * interacts with pharos agent to device details.
     *
     * @param dcaSpecificProperty This is the type parameter
     * @param projectId           id of the project
     * @return list<devices>
     * @throws IOException if there is any error
     */
    public List<Device> getDeviceStatus(final String dcaSpecificProperty, final String projectId) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", dcaSpecificProperty);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(pharosConfiguration.getUrl(), HttpMethod.GET,
                entity, String.class);
        String x = result.getBody();
        return csvParser(x, projectId);
    }

    /**
     * converts csv to list of devices.
     *
     * @param csvData   device details in csv form
     * @param projectId id of the project
     * @return list<devices>
     * @throws IOException if there is any error
     */
    private List<Device> csvParser(final String csvData, final String projectId) throws IOException {
        String[] csvArray = csvData.replace("\"", "").split("\\r?\\n");
        String[] keys = csvArray[0].split(",");
        List<Device> devices = new ArrayList<Device>();
        for (int valueIndex = 1; valueIndex < csvArray.length; valueIndex++) {
            String[] values = csvArray[valueIndex].split(",", -1);
            HashMap<String, String> deviceValues = new HashMap<String, String>();
            for (int keyIndex = 0; keyIndex < keys.length; keyIndex++) {
                deviceValues.put(keys[keyIndex], values[keyIndex]);
            }
            Device device = new Device(deviceValues);
            device.setProjectId(projectId);
            devices.add(device);
        }
        return devices;
    }
}