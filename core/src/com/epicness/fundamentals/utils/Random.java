package com.epicness.fundamentals.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Random {

    public static Color fullyRandomColor() {
        float r = MathUtils.random();
        float g = MathUtils.random();
        float b = MathUtils.random();
        return new Color(r, g, b, 1f);
    }

    public static Color randomColor() {
        int random = MathUtils.random(9);
        switch (random) {
            case 0:
                return Color.RED;
            case 1:
                return Color.ORANGE;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.CHARTREUSE;
            case 4:
                return Color.FOREST;
            case 5:
                return Color.SKY;
            case 6:
                return Color.CYAN;
            case 7:
                return Color.PURPLE;
            case 8:
                return Color.LIME;
            case 9:
            default:
                return Color.PINK;
        }
    }

    public static Color exclusiveRandomColor(Color excludedColor) {
        Color color;
        do {
            color = randomColor();
        } while (color == excludedColor);
        return color;
    }

    public static Array<Color> randomColors(int quantity) {
        Array<Color> colors = new Array<>();
        for (int i = 0; i < quantity; i++) {
            Color color;
            do {
                color = randomColor();
            }
            while (colors.contains(color, true));
            colors.add(color);
        }
        return colors;
    }
}