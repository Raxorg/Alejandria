package com.epicness.alejandria.showcase.constants;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.PINK;
import static com.badlogic.gdx.graphics.Color.YELLOW;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;

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
        COLOR_MAP.put(INITIAL_SIZE, new Color(0xB1A3FFFF));
        COLOR_MAP.put(INITIAL_SIZE / 2f, new Color(0xFFA599FF));
        COLOR_MAP.put(INITIAL_SIZE / 4f, new Color(0x94DBFFFF));
        COLOR_MAP.put(INITIAL_SIZE / 8f, new Color(0xFFD0A1FF));
        COLOR_MAP.put(INITIAL_SIZE / 16f, new Color(0xBDFFBFFF));
        COLOR_MAP.put(INITIAL_SIZE / 32f, PINK);
        COLOR_MAP.put(INITIAL_SIZE / 64f, YELLOW);
        COLOR_MAP.put(INITIAL_SIZE / 128f, BLACK);
    }
}