package com.epicness.alejandria.showcase.constants;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.BROWN;
import static com.badlogic.gdx.graphics.Color.CYAN;
import static com.badlogic.gdx.graphics.Color.GRAY;
import static com.badlogic.gdx.graphics.Color.LIME;
import static com.badlogic.gdx.graphics.Color.MAGENTA;
import static com.badlogic.gdx.graphics.Color.ORANGE;
import static com.badlogic.gdx.graphics.Color.PINK;
import static com.badlogic.gdx.graphics.Color.PURPLE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.Color.ROYAL;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.graphics.Color.YELLOW;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;

import com.badlogic.gdx.graphics.Color;

public class FunConstants {

    // Beeping Balls
    public static final Color[] BALL_COLORS = {
        BROWN,
        BROWN.cpy().lerp(RED, 0.5f),
        RED,
        RED.cpy().lerp(ORANGE, 0.5f),
        ORANGE,
        ORANGE.cpy().lerp(YELLOW, 0.5f),
        YELLOW,
        YELLOW.cpy().lerp(LIME, 0.5f),
        LIME,
        LIME.cpy().lerp(CYAN, 0.5f),
        CYAN,
        CYAN.cpy().lerp(ROYAL, 0.5f),
        ROYAL,
        ROYAL.cpy().lerp(BLUE, 0.5f),
        BLUE,
        BLUE.cpy().lerp(PURPLE, 0.5f),
        PURPLE,
        PURPLE.cpy().lerp(MAGENTA, 0.5f),
        MAGENTA,
        MAGENTA.cpy().lerp(PINK, 0.5f),
        PINK,
        PINK.cpy().lerp(WHITE, 0.5f),
        WHITE,
        WHITE.cpy().lerp(GRAY, 0.5f),
        GRAY,
        GRAY.cpy().lerp(BLACK, 0.5f)
    };
    public static final float SPACING = 15f;
    public static final int BALLS = 24;
    public static final float VOLUME = 0.4f;

    // Falling Sand
    public static final int SAND_DIMENSION = 50;
    public static final float SAND_SIZE = SHOWCASE_SIZE / SAND_DIMENSION;
    public static final float SAND_UPDATE_RATE = 0.035f;
}