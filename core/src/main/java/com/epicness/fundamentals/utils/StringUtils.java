package com.epicness.fundamentals.utils;

public class StringUtils {

    public static String capitalizeFirst(String target) {
        return target.substring(0, 1).toUpperCase() + target.substring(1);
    }
}