package com.epicness.fundamentals.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberUtils {

    public static int countDigits(int number) {
        int count = 0;
        while (number != 0) {
            number /= 10;
            count++;
        }
        return count;
    }

    public static int[] getDigits(int number) {
        List<Integer> digits = new ArrayList<>();
        collectDigits(number, digits);
        Collections.reverse(digits);
        return digits.stream().mapToInt(i -> i).toArray();
    }

    private static void collectDigits(int number, List<Integer> digits) {
        if (number / 10 > 0) {
            collectDigits(number / 10, digits);
        }
        digits.add(number % 10);
    }
}