package com.itt.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

public class ClipBoard {

    private static final Logger LOGGER = LogManager.getLogger(ClipBoard.class);

    public static String getCopiedText() {
        String clipBoardText = null;
        try {
            clipBoardText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            LOGGER.info("Text copied from clipboard is: [{}]", clipBoardText);
        } catch (Exception e) {
            Assert.fail("Error occurred while copying the data from clip board");
        }
        return clipBoardText;
    }

    public static boolean containsWords(String inputString, String[] items) {
        boolean found = true;
        for (String item : items) {
            if (!inputString.contains(item)) {
                found = false;
                break;
            }
        }
        return found;
    }
}
