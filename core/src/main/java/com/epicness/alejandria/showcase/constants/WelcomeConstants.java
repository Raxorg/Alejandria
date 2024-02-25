package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

public class WelcomeConstants {

    private static final float STRIPE_RESOLUTION = WINDOW_SIZE / 10f;
    public static final float SHADER_RESOLUTION = WINDOW_SIZE - STRIPE_RESOLUTION * 3f;
    public static final float CANVAS_WIDTH = CAMERA_WIDTH;
    public static final float CANVAS_HEIGHT = CAMERA_HEIGHT - SHOWCASE_STRIPE_HEIGHT * 3f;
    public static final float CANVAS_X = 0f;
    public static final float CANVAS_Y = SHOWCASE_STRIPE_HEIGHT * 2f;
    public static final float SHADER_X = STRIPE_RESOLUTION * 1.5f;
    public static final float SHADER_Y = STRIPE_RESOLUTION * 2f;
}