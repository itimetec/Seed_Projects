package com.itt.testExecution;

import com.itt.configurations.test.EnvConfig;
import com.itt.configurations.test.TestConfiguration;
import com.itt.constants.CleanUp;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static io.restassured.RestAssured.basic;

@Component
public class Environment {

    private static final Logger LOGGER = LogManager.getLogger(Environment.class);

    @Autowired
    private TestConfiguration configuration;

    public void configureAPIGlobalParameters() {
        RestAssured.baseURI = configuration.getApiBaseUri();
        RestAssured.authentication = basic(configuration.getUserName(), configuration.getPassword());
    }

    public void setUp(Method method) {
        if (getAnnotation(method) == null)
            return;
        configureEnvironment(method);
    }

    public void setPrecondition(Method method) {
        EnvConfig annotation = getAnnotation(method);
        if (annotation == null)
            return;

        String[] groups = annotation.startUp();
        if (groups.length == 0)
            return;
    }

    public void cleanUp(Method method) {
        EnvConfig annotation = getAnnotation(method);
        if (annotation == null)
            return;

        String[] groups = annotation.cleanUp();
        if (groups.length == 0)
            return;

        for (String group : groups)
            clean(group);
    }

    private void clean(String group) {
        switch (group) {
            case CleanUp.DRAFT_PROVIDER_CONTRACT:
                break;
            default:
                LOGGER.info("No Clean Up Activity Is Performed");
        }
    }

    private EnvConfig getAnnotation(Method method) {
        return method.getAnnotation(EnvConfig.class);
    }

    private void configureEnvironment(Method method) {
        setPrecondition(method);
    }
}
