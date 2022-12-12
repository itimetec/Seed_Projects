package com.itt.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public final class Generate {

    private static Random random = new Random();

    private Generate() {
    }

    public static String id() {
        return RandomStringUtils.randomAlphanumeric(32);
    }

    public static String randomString() {
        return "Automation_bot_" + RandomStringUtils.randomAlphanumeric(5);
    }

    public static String location() {
        return "Location:" + RandomStringUtils.randomAlphanumeric(5);
    }

    public static String randomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static int generateNumber(int length) {
        int min = (int) Math.pow(10, (long) length - 1);
        int max = (int) Math.pow(10, length);
        return random.nextInt(max - min) + min;
    }

    public static String generateDecimalNumber(int length) {
        int min = (int) Math.pow(10, (long) length - 1);
        int max = (int) Math.pow(10, length);
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return decimalFormat.format((random.nextInt(max - min) + min) / 100.00);
    }

    public static String randomLink() {
        return String.format("https://%s.com", randomString(7));
    }

    public static String generateRandomNumberBetween(int upper, int lower) {
        return String.valueOf((int) (Math.random() * (upper - lower)) + lower);
    }

    public static String generateRandomEmail() {
        return "testautomation" + RandomStringUtils.randomAlphabetic(7) + "@yourapp.com";
    }

    public static String generateRandomAlphabets(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomHexDigits(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < length) {
            stringBuilder.append(Integer.toHexString(random.nextInt()));
        }
        return stringBuilder.substring(0, length);
    }

    public static String generateRandomContact(List<String> contacts) {
        int randomIndex = random.nextInt(contacts.size());
        String randomContact = contacts.get(randomIndex);
        return randomContact;
    }
}
