package com.epicness.alejandria.showcase.constants;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.PINK;
import static com.badlogic.gdx.graphics.Color.YELLOW;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_BLUE;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_GREEN;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_ORANGE;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_PURPLE;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_RED;

import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;
import java.util.Map;

public class OptimizationConstants {

    public static final float QUAD_THICKNESS = 5f;
    public static final float DOT_SIZE = 25f;
    public static final float MARGIN = 15f;
    public static final float INITIAL_SIZE = SHOWCASE_SIZE - MARGIN * 2f;
    public static final Map<Float, Color> COLOR_MAP;

    static {
        COLOR_MAP = new HashMap<>();
        COLOR_MAP.put(INITIAL_SIZE, PASTEL_PURPLE);
        COLOR_MAP.put(INITIAL_SIZE / 2f, PASTEL_RED);
        COLOR_MAP.put(INITIAL_SIZE / 4f, PASTEL_BLUE);
        COLOR_MAP.put(INITIAL_SIZE / 8f, PASTEL_ORANGE);
        COLOR_MAP.put(INITIAL_SIZE / 16f, PASTEL_GREEN);
        COLOR_MAP.put(INITIAL_SIZE / 32f, PINK);
        COLOR_MAP.put(INITIAL_SIZE / 64f, YELLOW);
        COLOR_MAP.put(INITIAL_SIZE / 128f, BLACK);
    }
}