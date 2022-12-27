package com.itt.base;

import com.itt.util.apiValidations.JsonValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class APITestBase extends TestBase {

    private static final Logger LOGGER = LogManager.getLogger(APITestBase.class);

    @Autowired
    public JsonValidation jsonValidation;

}
