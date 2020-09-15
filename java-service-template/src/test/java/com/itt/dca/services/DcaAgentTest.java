package com.itt.dca.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.itt.dca.model.Device;
import com.itt.test_category.ServicesTests;
import com.itt.test_data.DeviceTestDataRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import com.itt.dca.DcaAgentService;

@Category(ServicesTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class DcaAgentTest {
    /**
     * spring creates an DcaAgentService instance.
     */
    @Autowired
    private DcaAgentService dcaAgentService;
    /**
     * spring creates an DeviceTestDataRepository instance.
     */
    @Autowired
    private DeviceTestDataRepository deviceTestDataRepository;

    @Test
    public void testGetLatestDeviceStatus() throws IOException {

        List<Device> devicesCollection = deviceTestDataRepository.getDevices();

        assertThat(dcaAgentService.getDeviceStatus("5abc1240239", true).size()).isEqualTo(devicesCollection.size());
    }
}
