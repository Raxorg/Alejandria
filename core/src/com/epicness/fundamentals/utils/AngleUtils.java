package com.epicness.fundamentals.utils;

public class AngleUtils {

    public static float normalize(float angle) {
        return (angle % 360f) + (angle < 0f ? 360f : 0f);
    }

    public static float circularClamp(float angle, float min, float max) {
        float diff = Math.abs(min - max) / 2f;
        angle = angle < min ? (angle + diff < min ? max : min) : (angle > max ? (angle - diff > max ? min : max) : angle);
        return angle;
    }
}