package com.itt.resources.ui;

import com.itt.constants.ResourceName;
import com.itt.util.FileReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser {

    private String name;
    private String job;


    public static CreateUser getInstance() {
        CreateUser createUser = (CreateUser) FileReader.loadTestData(ResourceName.CREATE_USER, CreateUser.class);
        return createUser;
    }

}
