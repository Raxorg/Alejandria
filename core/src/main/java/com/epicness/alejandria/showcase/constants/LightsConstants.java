package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_TOP;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

public class LightsConstants {

    // Simple Lights
    public static final int SHAPE_COUNT = 10;

    public static final float CIRCLE_RADIUS = 50f;
    public static final float MIN_CIRCLE_X = CIRCLE_RADIUS;
    public static final float MAX_CIRCLE_X = VIEWPORT_WIDTH - CIRCLE_RADIUS * 2f;
    public static final float MIN_CIRCLE_Y = SHOWCASE_STRIPE_HEIGHT + CIRCLE_RADIUS;
    public static final float MAX_CIRCLE_Y = SHOWCASE_TOP - CIRCLE_RADIUS * 2f;

    public static final float RECTANGLE_SIZE = 100f;
    public static final float MIN_RECTANGLE_X = 0f;
    public static final float MAX_RECTANGLE_X = VIEWPORT_WIDTH - RECTANGLE_SIZE;
    public static final float MIN_RECTANGLE_Y = SHOWCASE_STRIPE_HEIGHT;
    public static final float MAX_RECTANGLE_Y = SHOWCASE_TOP - RECTANGLE_SIZE;
}