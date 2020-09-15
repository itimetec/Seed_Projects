package com.itt.dca.pharos;

import com.itt.mock.MockRestService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * intializes the pharos related data.
 */
@Configuration("pharosConfiguration")
@ConfigurationProperties(prefix = "pharos")
@Slf4j
@Data
@DependsOn("mockRestService")
public class PharosConfiguration {

    /**
     * url of pharos api.
     */
    private String url;
    /**
     * mockurl.
     */
    private String mockUrl;
    /**
     * flag to enable the mocking.
     */
    private Boolean useMock;
    /**
     * gets authorized data.
     * @return authorization
     */
    public String getAuthorization() {
        return authorization;
    }

    /**
     * gets authorized data.
     */
    private String authorization;
    /**
     *Spring intializes the mockRestService.
     */
    @Autowired
    private MockRestService mockRestService;

    /**
     * get the url based on the mocking flag.
     * @return url/mockurl
     */
    public String getUrl() {
        if (useMock) {
            return mockUrl;
        } else {
            return url;
        }
    }

    /**
     *intializes the Pharos configuration.
     * @param mockRestService gets all mock details
     */
    public PharosConfiguration(final MockRestService mockRestService) {
        this.mockRestService = mockRestService;
        this.setUpMockEndPoint();
    }

    /**
     * sets up mock with its end point.
     */
    private void setUpMockEndPoint() {
        mockRestService.setupMock("GET", "/pharos/Reports/Meters", "PharosTestResponse");
    }
}
