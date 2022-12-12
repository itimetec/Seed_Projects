package com.itt.test.api.base;

import com.itt.api.authmanager.APISpecificationManager;
import com.itt.api.services.UserServices;
import com.itt.base.APITestBase;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ProjectAPITestBase extends APITestBase {

    @Autowired
    protected UserServices userServices;

    @Autowired
    protected APISpecificationManager manager;
}
