package com.itt.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.CharacterData;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;


public class XmlHandler {

    private static final Logger LOGGER = LogManager.getLogger(XmlHandler.class);

    public static String convertDocumentToString(Document document) {
        try {
            StringWriter stringWriter = new StringWriter();
            TransformerFactory.newDefaultInstance().newTransformer().transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (TransformerException e) {
            LOGGER.warn(String.format("There is an error to read document - Exception: %s", e));
        }
        return null;
    }

    public static Document convertStringToDocument(String xmlStr) {
        try {
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xmlStr));
            Document document = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder().parse(inputSource);
            document.getDocumentElement().normalize();
            return document;
        } catch (Exception e) {
            LOGGER.warn(String.format("There is an error in converting xml string to document - Exception: %s", e));
        }
        return null;
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData characterData = (CharacterData) child;
            return characterData.getData();
        }
        return null;
    }

    public static NodeList getNodeByTagName(Document document, String tagName, String subTag) {
        Element element = (Element) document.getElementsByTagName(tagName).item(0);
        return element.getElementsByTagName(subTag);
    }

}
