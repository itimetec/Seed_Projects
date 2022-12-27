package com.itt.util;

import com.itt.constants.ResourceName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;

public class CSVFileHandler {

    private static final Logger LOGGER = LogManager.getLogger(CSVFileHandler.class);

    private static final String TEST_DATA_PATH = "src/test/resources/TestData/";

    private static String getCsvFileExtension() {
        return ".csv";
    }

    public static String getResourcePath(ResourceName name) {
        return Paths.get(TEST_DATA_PATH, name.toString()).toAbsolutePath().toString() + getCsvFileExtension();
    }

    public static Boolean isValuePresent(ResourceName name, String word) {
        Boolean status = false;

        try (FileReader fileReader = new FileReader(getResourcePath(name));
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = "";
            String[] fileData;
            while ((line = bufferedReader.readLine()) != null) {
                fileData = line.split(",");
                for (String data : fileData) {
                    data = data.replaceAll("\"", "");
                    if (data.equalsIgnoreCase(word)) {
                        status = true;
                        break;
                    }
                }
            }
        } catch (Exception exception) {
            LOGGER.info("There is an error to find -%s in file. Exception: ", word, exception);
            return false;
        }
        return status;
    }

}
