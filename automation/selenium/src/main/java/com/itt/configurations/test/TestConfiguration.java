package com.itt.configurations.test;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Lazy
@Getter
@Configuration
@ComponentScan(basePackages = {"com.itt"})
public class TestConfiguration {

    @Configuration
    @PropertySource("classpath:properties/test.properties")
    @Profile({"test"})
    static class Test {
    }

    @Configuration
    @PropertySource("classpath:properties/regression.properties")
    @Profile({"regression"})
    static class Regression {
    }

    @Value("${uiBaseUri}")
    private String uiBaseUri;

    @Value("${apiBaseUri}")
    private String apiBaseUri;


    @Bean
    public static Object getInstance(Class classType) {
        return MyApplicationContext.getContext().getBean(classType);
    }
    public String getUserName() {
        return System.getProperty("userName");
    }
    public String getPassword() {
        return System.getProperty("password");
    }
    public String getActiveProfile() {
        return System.getProperty("spring.profiles.active");
    }
}