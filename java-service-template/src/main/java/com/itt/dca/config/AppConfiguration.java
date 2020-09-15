package com.itt.dca.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * intializes the application related data.
 */
@Configuration("configuration")
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfiguration {

    /**
     * gives the tenant details.
     * @return returns tenant
     */
    public String getTenant() {
        return tenant;
    }
    /**
     * tenant of the project.
     */
    private String tenant;
}
