package com.epicness.fundamentals.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Random {

    public static Color opaqueColor() {
        float r = MathUtils.random();
        float g = MathUtils.random();
        float b = MathUtils.random();
        return new Color(r, g, b, 1f);
    }

    public static Color rainbowColor() {
        int random = MathUtils.random(10);
        return switch (random) {
            case 0 -> Color.RED;
            case 1 -> Color.ORANGE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.CHARTREUSE;
            case 4 -> Color.LIME;
            case 5 -> Color.FOREST;
            case 6 -> Color.CYAN;
            case 7 -> Color.SKY;
            case 8 -> Color.BLUE;
            case 9 -> Color.PURPLE;
            default -> Color.PINK;
        };
    }

    public static Color rainbowExcludedColor(Color excludedColor) {
        Color color;
        do {
            color = rainbowColor();
        } while (color.equals(excludedColor));
        return color;
    }

    public static Array<Color> randomColors(int quantity) {
        Array<Color> colors = new Array<>();
        for (int i = 0; i < quantity; i++) {
            Color color;
            do {
                color = rainbowColor();
            }
            while (colors.contains(color, true));
            colors.add(color);
        }
        return colors;
    }
}