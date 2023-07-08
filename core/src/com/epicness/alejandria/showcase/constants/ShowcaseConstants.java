package com.epicness.alejandria.showcase.constants;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.GRASS;

import com.badlogic.gdx.graphics.Color;

public class ShowcaseConstants {

    public static final int WINDOW_SIZE = 600;

    public static final float STRIPE_HEIGHT = 100f;
    public static final float TOP_STRIPE_Y = CAMERA_HEIGHT - STRIPE_HEIGHT;

    public static final float SHOWCASE_BUTTON_SIZE = 100f;
    public static final float GITHUB_BUTTON_X = CAMERA_HALF_WIDTH - SHOWCASE_BUTTON_SIZE * 1.25f;
    public static final float INFO_BUTTON_X = CAMERA_HALF_WIDTH + SHOWCASE_BUTTON_SIZE * 0.25f;
    public static final float NEXT_BUTTON_X = CAMERA_WIDTH - SHOWCASE_BUTTON_SIZE;

    public static final float SHOWCASE_SIZE = CAMERA_HEIGHT - STRIPE_HEIGHT * 2f;
    public static final float SHOWCASE_X = SHOWCASE_BUTTON_SIZE;
    public static final float SHOWCASE_Y = STRIPE_HEIGHT;
    public static final Color SHOWCASE_BACKGROUND_COLOR = GRASS;

    public static final String GITHUB_ROOT = "https://github.com/Raxorg/Alejandria/blob/master/core/src/";
}