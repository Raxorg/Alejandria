package com.epicness.alejandria.showcase.constants;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.badlogic.gdx.graphics.Color.ORANGE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.Color.YELLOW;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.GRASS;

import com.badlogic.gdx.graphics.Color;

public class ShowcaseConstants {

    public static final int WINDOW_SIZE = 600;

    public static final float SHOWCASE_STRIPE_HEIGHT = 100f;
    public static final float TOP_STRIPE_Y = CAMERA_HEIGHT - SHOWCASE_STRIPE_HEIGHT;

    public static final float SHOWCASE_BUTTON_SIZE = 100f;
    public static final float GITHUB_BUTTON_X = CAMERA_HALF_WIDTH - SHOWCASE_BUTTON_SIZE * 1.25f;
    public static final float INFO_BUTTON_X = CAMERA_HALF_WIDTH + SHOWCASE_BUTTON_SIZE * 0.25f;
    public static final float NEXT_BUTTON_X = CAMERA_WIDTH - SHOWCASE_BUTTON_SIZE;

    public static final float SHOWCASE_SIZE = CAMERA_HEIGHT - SHOWCASE_STRIPE_HEIGHT * 2f;
    public static final float SHOWCASE_HALF_SIZE = SHOWCASE_SIZE / 2f;
    public static final float SHOWCASE_X = SHOWCASE_BUTTON_SIZE;
    public static final float SHOWCASE_Y = SHOWCASE_STRIPE_HEIGHT;
    public static final float SHOWCASE_TOP = SHOWCASE_STRIPE_HEIGHT + SHOWCASE_SIZE;
    public static final Color SHOWCASE_BACKGROUND_COLOR = GRASS;

    public static final Color[] BASIC_COLORS = new Color[]{RED, ORANGE, YELLOW, GREEN, BLUE};

    public static final String GITHUB_ROOT = "https://github.com/Raxorg/Alejandria/blob/master/core/src/main/java/";
}