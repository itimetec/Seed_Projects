package com.itt.constants;

public enum ResourceName {


    TEST_LOGIN_DATA("LoginTestData"),
    CREATE_USER_WITH_YAML("CreateUserWithYAML"),
    DRIVER_CONFIGURATION("DriverConfiguration"),
    CREATE_USER("CreateUser");

    private String name;

    ResourceName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
