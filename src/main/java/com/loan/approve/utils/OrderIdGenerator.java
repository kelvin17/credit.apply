package com.loan.approve.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderIdGenerator {
    public static String generate(String userName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(new Date());

        String userPart = getLastNChars(userName, 4);
        String randomPart = generateRandomLetters(4);

        return "APPROVAL" + timestamp + userPart + randomPart;
    }

    private static String getLastNChars(String input, int n) {
        if (input == null || input.length() < n) {
            return String.format("%" + n + "s", input == null ? "" : input).replace(' ', '0');
        }
        return input.substring(input.length() - n);
    }

    private static String generateRandomLetters(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(OrderIdGenerator.generate("Yanqing"));
    }
}
