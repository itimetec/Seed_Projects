package com.itt.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.itt.constants.FileType;
import com.itt.constants.ResourceName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class FileReader {

    private static final Logger LOGGER = LogManager.getLogger(FileReader.class);
    private static final String DRIVER_DIR = "driver/";
    private static final String TEMPLATES_DIR = "templates/";
    private static final String TEST_DATA_DIR = "testData/";
    private static final String API_TEST_DATA_DIR = "apiTestData/";
    private static final String TEST_DATA_PATH = "src/test/resources/TestData/";
    private static final String TEST_DATA_DOWNLOAD_PATH = "src/test/resources/TestData/downloadedFiles";
    private static final String JSON_DATA_PATH = "src/main/resources/templates/";

    public static Object loadDriverFile(ResourceName resourceName, Class containerClass) {
        return load(resourceName, containerClass, DRIVER_DIR, FileType.YAML);
    }

    public static Object loadTemplate(ResourceName resourceName, Class containerClass) {
        return load(resourceName, containerClass, TEMPLATES_DIR, FileType.JSON);
    }

    public static Object loadTestData(ResourceName resourceName, Class containerClass) {
        return load(resourceName, containerClass, TEST_DATA_DIR, FileType.JSON);
    }

    public static Object loadAPITestData(ResourceName resourceName, Class containerClass) {
        return deserializeToObject(resourceName, containerClass, API_TEST_DATA_DIR, FileType.YAML);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Object load(ResourceName resourceName, Class containerClass, String directory, FileType fileType) {
        String resourceFile = resourceName.toString() + fileType.toString();
        try (InputStream templateStream = FileReader.class.getResourceAsStream("/" + directory + resourceFile)) {
            if (templateStream == null)
                throw new IOException("Invalid Resource Name: " + resourceName);
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return (Object) mapper.readValue(templateStream, containerClass);
        } catch (IOException e) {
            LOGGER.error("Unable to load the fie: [{}]", e.getStackTrace());
        }
        return null;
    }

    public static Map<String, Object> getMapFromJson(ResourceName resourceName) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = mapper.readValue(
                    new File(Paths.get(TEST_DATA_PATH, resourceName.toString()).toAbsolutePath().toString() + ".json"),
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (Exception e) {
            LOGGER.info("There is an error to read file - %s. Exception: %s", resourceName, e);
        }
        return jsonMap;
    }

    public static <T> Object[][] loadTemplates(ResourceName resourceName, Class<T[][]> convertType) {
        return deserializeToObject(resourceName, convertType, TEMPLATES_DIR, FileType.JSON);
    }

    private static <T> Object[][] deserializeToObject(ResourceName resourceName, Class<T[][]> convertType, String directory, FileType fileType) {
        String resourceFile = resourceName.toString() + fileType.toString();
        try (InputStream templateStream = FileReader.class.getResourceAsStream("/" + directory + resourceFile)) {
            if (templateStream == null)
                throw new IOException("Invalid Resource Name: " + resourceName);
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            return (Object[][]) mapper.readValue(templateStream, convertType);
        } catch (Exception e) {
            LOGGER.info("There is an error to read file - %s. Exception: %s", resourceName, e);
        }
        return null;
    }

    public static String getResourcePath(ResourceName name) {
        return Paths.get(TEST_DATA_PATH, name.toString()).toAbsolutePath().toString() + FileType.XML.toString();
    }

    public static String getResourcePathForXLSC(ResourceName name, String filePath) {
        return Paths.get(filePath, name.toString()).toAbsolutePath().toString() + FileType.XLSX.toString();
    }

    public static String getResourcePathForJSON(String fileName) {
        return Paths.get(JSON_DATA_PATH, fileName).toAbsolutePath().toString() + FileType.JSON;
    }

    public static void removeFile(ResourceName name, String directory) {
        File file = new File(getResourcePath(name, directory));
        try {
            if (file.exists()) {
                LOGGER.info("Deleting File " + name);
                Assert.assertTrue(file.delete(), "File is not deleted");
            }
        } catch (Exception e) {
            LOGGER.info("There is an error to delete file - %s. Exception: ", name, e);
        }
    }

    public static void removeFile(String fileName) {
        File file = new File((Paths.get(TEST_DATA_PATH, fileName)).toString());
        try {
            if (file.exists()) {
                LOGGER.info("Deleting File " + fileName);
                Assert.assertTrue(file.delete(), "File is not deleted");
            }
        } catch (Exception e) {
            LOGGER.info("There is an error to delete file - %s. Exception: ", fileName, e);
        }
    }

    public static String getResourcePath(ResourceName name, String directory) {
        switch (directory) {
            case "downloadedFiles":
                return getResourcePathForXLSC(name, TEST_DATA_DOWNLOAD_PATH);
            case "testData":
                return getResourcePathForXLSC(name, TEST_DATA_PATH);
            default:
                return getResourcePathForXLSC(name, TEST_DATA_DIR);
        }
    }
}
