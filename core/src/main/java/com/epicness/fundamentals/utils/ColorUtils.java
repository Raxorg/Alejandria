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
import static com.epicness.fundamentals.constants.SharedConstants.DIRT;
import static com.epicness.fundamentals.constants.SharedConstants.GRASS;
import static com.epicness.fundamentals.constants.SharedConstants.PASTEL_BLUE;
import static com.epicness.fundamentals.constants.SharedConstants.PASTEL_GREEN;
import static com.epicness.fundamentals.constants.SharedConstants.PASTEL_ORANGE;
import static com.epicness.fundamentals.constants.SharedConstants.PASTEL_PURPLE;
import static com.epicness.fundamentals.constants.SharedConstants.PASTEL_RED;
import static com.epicness.fundamentals.constants.SharedConstants.PASTEL_YELLOW;

import com.badlogic.gdx.graphics.Color;

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
}