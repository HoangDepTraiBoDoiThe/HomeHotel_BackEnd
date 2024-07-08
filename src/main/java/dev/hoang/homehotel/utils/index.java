package dev.hoang.homehotel.utils;

import java.util.Random;

public class index {
    public static String generateNumericCode(int length) {
        Random random = new Random();
        int number = random.nextInt((int) Math.pow(10, length));
        return String.format("%0" + length + "d", number);
    }
}
