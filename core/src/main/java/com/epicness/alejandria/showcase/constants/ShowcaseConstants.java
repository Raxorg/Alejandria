package com.epicness.alejandria.showcase.constants;

import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.GRASS;

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

    public static final Color PASTEL_RED = new Color(0xFFA599FF);
    public static final Color PASTEL_GREEN = new Color(0xBDFFBFFF);
    public static final Color PASTEL_BLUE = new Color(0x94DBFFFF);
    public static final Color PASTEL_YELLOW = new Color(0xFFFF99FF);
    public static final Color PASTEL_ORANGE = new Color(0xFFD0A1FF);
    public static final Color PASTEL_PURPLE = new Color(0xB1A3FFFF);

    public static final String GITHUB_ROOT = "https://github.com/Raxorg/Alejandria/blob/master/core/src/main/java/";
}