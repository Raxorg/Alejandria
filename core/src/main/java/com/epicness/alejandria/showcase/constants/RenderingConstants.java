package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_HALF_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

public class RenderingConstants {

    // Shape Rendering
    public static final int COLOR_STEPS = 25;
    public static final int COILS = 20;
    public static final float X_STEP = VIEWPORT_HALF_WIDTH / COILS;
    public static final float Y_STEP = SHOWCASE_HALF_SIZE / COILS;
}