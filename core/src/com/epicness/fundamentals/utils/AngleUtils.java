package com.epicness.fundamentals.utils;

public class AngleUtils {

    public static float normalize(float angle) {
        return (angle % 360f) + (angle < 0f ? 360f : 0f);
    }
}