package com.itt.dca.model;

import lombok.Data;

import java.util.HashMap;

/**
 * Device class which manages deviceDetails.
 * @author triveni.k
 */
@Data
public class Device {

    /**
     *default constructor.
     */
    public Device() {
    }

    /**
     *constructor that intializes the class variables.
     * @param deviceValues contains device properties and values
     */
    public Device(final HashMap<String, String> deviceValues) {
        this.make = deviceValues.get("Model");
        this.model = deviceValues.get("Manufacturer");
        this.mac = deviceValues.get("MAC Address");
    }
    /**
     * mac of device.
     */
    private String mac;

    /**
     * model of device.
     */
    private String model;

    /**
     * make of device.
     */
    private String make;

    /**
     * projectId of device.
     */
    private String projectId;
}