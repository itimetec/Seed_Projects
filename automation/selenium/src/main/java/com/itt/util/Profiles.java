package com.itt.util;

public class Profiles {

    public static String getActiveProfile() {
        return System.getProperty("spring.profiles.active");
    }

}
