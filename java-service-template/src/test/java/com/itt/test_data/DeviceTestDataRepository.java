
package com.itt.test_data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itt.dca.model.Device;
import com.itt.mock.ResourceFileLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Configuration
public class DeviceTestDataRepository {

    private static List<Device> devices;

    public DeviceTestDataRepository() {
    }

    public synchronized List<Device> getDevices() {

        if (devices == null) {
            devices = getTestData("DCATestData");
        }
        return devices;
    }

    /**
     * Parses the test data file and returns a HashMap<String, T> format where T
     * represents the type of object and the String is the key or name of the
     * object.
     *
     * @param classT class of Type T.
     * @return HashMap containing HashMap of T type of objects
     */
    public <T> List<Device> getTestData(final String testDataFileName) {

        List<Device> items = new ArrayList<Device>();

        try {
            String testData = ResourceFileLoader.loadResource(testDataFileName);
            ObjectMapper mapper = new ObjectMapper();
            items = mapper.readValue(testData, new TypeReference<List<Device>>() { });

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Error loading user data", ex);
        }
        return items;
    }

    /**
     * Retrieves an object of type T from the generic Hashmap given it's key
     *
     * @param testDataMap the generic hashmap containing the test data.
     * @param key name of the key
     * @param classT class of Object type T
     * @return T object
     * @throws JsonProcessingException
     * @throws IOException
     */
    private <T> T getObjectT(final HashMap testDataMap, final String key, final Class<T> classT)
            throws JsonProcessingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        HashMap map = (HashMap) testDataMap.get(key);
        String finalJson = mapper.writeValueAsString(map);
        T item = mapper.readValue(finalJson, classT);
        return item;
    }

}
