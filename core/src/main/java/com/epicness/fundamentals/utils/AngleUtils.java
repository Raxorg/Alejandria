package com.epicness.fundamentals.utils;

import static com.badlogic.gdx.math.MathUtils.radDeg;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class AngleUtils {

    public static float normalize(float angle) {
        return (angle % 360f) + (angle < 0f ? 360f : 0f);
    }

    public static float circularClamp(float angle, float min, float max) {
        float diff = Math.abs(min - max) / 2f;
        angle = angle < min ? (angle + diff < min ? max : min) : (angle > max ? (angle - diff > max ? min : max) : angle);
        return angle;
    }

    public static float degreesBetweenPoints(float x1, float y1, float x2, float y2) {
        return radDeg * MathUtils.atan2((y1 - y2), (x1 - x2));
    }

    public static float degreesBetweenPoints(Vector2 pointA, Vector2 pointB) {
        return degreesBetweenPoints(pointA.x, pointA.y, pointB.x, pointB.y);
    }
}