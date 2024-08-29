package com.epicness.fundamentals.utils;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.BROWN;
import static com.badlogic.gdx.graphics.Color.CHARTREUSE;
import static com.badlogic.gdx.graphics.Color.CYAN;
import static com.badlogic.gdx.graphics.Color.FOREST;
import static com.badlogic.gdx.graphics.Color.GRAY;
import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.badlogic.gdx.graphics.Color.MAGENTA;
import static com.badlogic.gdx.graphics.Color.OLIVE;
import static com.badlogic.gdx.graphics.Color.ORANGE;
import static com.badlogic.gdx.graphics.Color.PURPLE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.Color.SALMON;
import static com.badlogic.gdx.graphics.Color.TEAL;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.graphics.Color.YELLOW;
import static com.epicness.fundamentals.constants.ColorConstants.DIRT;
import static com.epicness.fundamentals.constants.ColorConstants.GRASS;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_BLUE;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_GREEN;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_ORANGE;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_PURPLE;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_RED;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_YELLOW;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

import java.util.HashMap;
import java.util.Map;

public class ColorUtils {

    private static final Map<Color, String> colorStringMap;
    private static final Map<String, Color> stringColorMap;

    static {
        colorStringMap = new HashMap<>();
        stringColorMap = new HashMap<>();
        registerPair(BLACK, "black");
        registerPair(GRAY, "gray");
        registerPair(WHITE, "white");
        registerPair(SALMON, "salmon");
        registerPair(BROWN, "brown");
        registerPair(PURPLE, "purple");
        registerPair(MAGENTA, "magenta");
        registerPair(RED, "red");
        registerPair(DIRT, "dirt");
        registerPair(ORANGE, "orange");
        registerPair(YELLOW, "yellow");
        registerPair(CHARTREUSE, "chartreuse");
        registerPair(GREEN, "green");
        registerPair(FOREST, "forest");
        registerPair(GRASS, "grass");
        registerPair(OLIVE, "olive");
        registerPair(TEAL, "teal");
        registerPair(CYAN, "cyan");
        registerPair(BLUE, "blue");
        registerPair(PASTEL_PURPLE, "pastel purple");
        registerPair(PASTEL_RED, "pastel red");
        registerPair(PASTEL_ORANGE, "pastel orange");
        registerPair(PASTEL_YELLOW, "pastel yellow");
        registerPair(PASTEL_GREEN, "pastel green");
        registerPair(PASTEL_BLUE, "pastel blue");
    }

    private static void registerPair(Color color, String colorString) {
        colorStringMap.put(color, colorString);
        stringColorMap.put(colorString, color);
    }

    public static String stringFromColor(Color color) {
        return colorStringMap.get(color);
    }

    public static Color colorFromString(String colorString) {
        return stringColorMap.get(colorString);
    }

    public static int HSVtoRGB(float h, float s, float v) {
        int r, g, b;
        if (s == 0f) {
            r = g = b = (int) (v * 255f + 0.5f);
        } else {
            float hueSector = (h - MathUtils.floor(h)) * 6f;
            float fraction = hueSector - MathUtils.floor(hueSector);
            float p = v * (1f - s);
            float q = v * (1f - s * fraction);
            float t = v * (1f - s * (1f - fraction));
            switch ((int) hueSector) {
                case 0:
                    r = (int) (v * 255f + 0.5f);
                    g = (int) (t * 255f + 0.5f);
                    b = (int) (p * 255f + 0.5f);
                    break;
                case 1:
                    r = (int) (q * 255f + 0.5f);
                    g = (int) (v * 255f + 0.5f);
                    b = (int) (p * 255f + 0.5f);
                    break;
                case 2:
                    r = (int) (p * 255f + 0.5f);
                    g = (int) (v * 255f + 0.5f);
                    b = (int) (t * 255f + 0.5f);
                    break;
                case 3:
                    r = (int) (p * 255f + 0.5f);
                    g = (int) (q * 255f + 0.5f);
                    b = (int) (v * 255f + 0.5f);
                    break;
                case 4:
                    r = (int) (t * 255f + 0.5f);
                    g = (int) (p * 255f + 0.5f);
                    b = (int) (v * 255f + 0.5f);
                    break;
                default:
                    r = (int) (v * 255f + 0.5f);
                    g = (int) (p * 255f + 0.5f);
                    b = (int) (q * 255f + 0.5f);
            }
        }

        return 0xff000000 | r << 16 | g << 8 | b;
    }
}