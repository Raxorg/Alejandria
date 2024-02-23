package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

public class RaymarchingConstants {

    private static final float STRIPE_RESOLUTION = WINDOW_SIZE / 10f;
    public static final float SHADER_RESOLUTION = WINDOW_SIZE - STRIPE_RESOLUTION * 2f;
    public static final float CANVAS_WIDTH = CAMERA_WIDTH;
    public static final float CANVAS_HEIGHT = CAMERA_HEIGHT - SHOWCASE_STRIPE_HEIGHT * 2f;
    public static final float SHADER_POSITION = STRIPE_RESOLUTION;
}