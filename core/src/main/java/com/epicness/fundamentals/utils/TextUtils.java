package com.epicness.fundamentals.utils;

import static com.epicness.fundamentals.constants.ColorConstants.ANSI_CYAN;
import static com.epicness.fundamentals.constants.ColorConstants.ANSI_RED;
import static com.epicness.fundamentals.constants.ColorConstants.ANSI_RESET;
import static com.epicness.fundamentals.constants.ColorConstants.ANSI_YELLOW;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.constants.ColorConstants;
import com.epicness.fundamentals.stuff.Text;

public class TextUtils {

    private static Vector2 getTextSize(BitmapFont font, String text) {
        Vector2 textSize = new Vector2();
        GlyphLayout layout = new GlyphLayout(font, text);
        textSize.x = layout.width;
        textSize.y = layout.height;
        return textSize;
    }

    private static Vector2 getTextSize(BitmapFont font, String text, float targetWidth, int hAlign, boolean wrap,
                                       String truncate) {
        Vector2 textSize = new Vector2();
        GlyphLayout layout = new GlyphLayout(font, text, 0, text.length(), font.getColor(), targetWidth, hAlign, wrap, truncate);
        textSize.x = layout.width;
        textSize.y = layout.height;
        return textSize;
    }

    public static float getTextWidth(BitmapFont font, String text) {
        return getTextSize(font, text).x;
    }

    public static float getTextWidth(BitmapFont font, String text, float targetWidth, int hAlign, boolean wrap,
                                     String truncate) {
        return getTextSize(font, text, targetWidth, hAlign, wrap, truncate).x;
    }

    public static float getTextWidth(Text text) {
        return getTextWidth(
            text.getFont(),
            text.getText(),
            text.getWidth(),
            text.getHAlign(),
            true,
            text.getTruncate());
    }

    public static float getTextHeight(BitmapFont font, String text) {
        return getTextSize(font, text).y;
    }

    public static float getTextHeight(BitmapFont font, String text, float targetWidth, int hAlign, boolean wrap,
                                      String truncate) {
        return getTextSize(font, text, targetWidth, hAlign, wrap, truncate).y;
    }

    public static float getTextHeight(Text text) {
        return getTextHeight(
            text.getFont(),
            text.getText(),
            text.getWidth(),
            text.getHAlign(),
            true,
            text.getTruncate());
    }

    public static BitmapFont copyOf(BitmapFont font) {
        return new BitmapFont(font.getData().fontFile);
    }

    /**
     * Changes the color of text displayed in the console<br>
     * Check {@link ColorConstants} for available colors
     */
    public static String colorString(String string, String color) {
        return color + string + ANSI_RESET;
    }

    public static String redString(String string) {
        return colorString(string, ANSI_RED);
    }

    public static String yellowString(String string) {
        return colorString(string, ANSI_YELLOW);
    }

    public static String cyanString(String string) {
        return colorString(string, ANSI_CYAN);
    }

    public static void colorSout(String string, String color) {
        System.out.println(colorString(string, color));
    }
}