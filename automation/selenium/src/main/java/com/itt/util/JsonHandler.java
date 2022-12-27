package com.itt.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHandler {

    private static final Logger LOGGER = LogManager.getLogger(JsonHandler.class);

    public static String getJsonAttributeValue(String jsonStr, String attributeName) {
        String attributeValue = null;
        try {
            JSONObject json = new JSONObject(jsonStr);
            attributeValue = json.getString(attributeName);
        } catch (JSONException e) {
            LOGGER.info("Unable to convert String to JSON - %s. JSONException: ", e);
        }
        return attributeValue;
    }

    public static JSONObject getJSONObjectForString(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException exception) {
            LOGGER.info("Unable to get the JSON object - %s. JSONException: ", exception);
            throw new RuntimeException("Unable to get the JSON object");
        }
    }

    public static int getJSONStatusCode(JSONObject jsonObject) {
        try {
            return (Integer) jsonObject.get("statusCode");
        } catch (JSONException jsonException) {
            LOGGER.info("Unable to get status code : ", jsonException.getMessage());
        }
        return 0;
    }
}
