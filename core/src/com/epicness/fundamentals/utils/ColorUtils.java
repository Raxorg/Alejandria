package com.epicness.fundamentals.utils;

import com.badlogic.gdx.graphics.Color;

public class ColorUtils {

    public static String stringFromColor(Color color) {
        if (color.equals(Color.WHITE)) {
            return "white";
        }
        if (color.equals(Color.RED)) {
            return "red";
        }
        if (color.equals(Color.PURPLE)) {
            return "purple";
        }
        if (color.equals(Color.SALMON)) {
            return "salmon";
        }
        if (color.equals(Color.ORANGE)) {
            return "orange";
        }
        if (color.equals(Color.YELLOW)) {
            return "yellow";
        }
        if (color.equals(Color.OLIVE)) {
            return "olive";
        }
        if (color.equals(Color.CHARTREUSE)) {
            return "chartreuse";
        }
        if (color.equals(Color.FOREST)) {
            return "forest";
        }
        if (color.equals(Color.TEAL)) {
            return "teal";
        }
        if (color.equals(Color.CYAN)) {
            return "cyan";
        }
        if (color.equals(Color.BLUE)) {
            return "blue";
        }
        if (color.equals(Color.BROWN)) {
            return "brown";
        }
        if (color.equals(Color.MAGENTA)) {
            return "magenta";
        }
        return "gray";
    }

    public static Color colorFromString(String color) {
        switch (color) {
            case "white":
                return Color.WHITE;
            case "red":
                return Color.RED;
            case "purple":
                return Color.PURPLE;
            case "salmon":
                return Color.SALMON;
            case "orange":
                return Color.ORANGE;
            case "yellow":
                return Color.YELLOW;
            case "olive":
                return Color.OLIVE;
            case "chartreuse":
                return Color.CHARTREUSE;
            case "forest":
                return Color.FOREST;
            case "teal":
                return Color.TEAL;
            case "cyan":
                return Color.CYAN;
            case "blue":
                return Color.BLUE;
            case "brown":
                return Color.BROWN;
            case "magenta":
                return Color.MAGENTA;
        }
        return Color.GRAY;
    }
}