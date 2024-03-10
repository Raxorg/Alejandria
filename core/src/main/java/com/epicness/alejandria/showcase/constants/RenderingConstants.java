package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

public class RenderingConstants {

    // Shape Rendering
    public static final int COLOR_STEPS = 25;
    public static final int COILS = 20;
    public static final float X_STEP = CAMERA_WIDTH / 2 / COILS;
    public static final float Y_STEP = SHOWCASE_SIZE / 2 / COILS;
}