package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.STRIPE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;

public class WelcomeConstants {

    private static final float STRIPE_RESOLUTION = WINDOW_SIZE / 10f;
    public static final float SHADER_RESOLUTION = WINDOW_SIZE - STRIPE_RESOLUTION * 4f;
    public static final float CANVAS_SIZE = CAMERA_HEIGHT - STRIPE_HEIGHT * 4f;
    public static final float SHADER_POSITION = STRIPE_RESOLUTION * 2f;
}