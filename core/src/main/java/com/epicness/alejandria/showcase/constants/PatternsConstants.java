package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_BLUE;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_GREEN;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_ORANGE;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_PURPLE;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_RED;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_YELLOW;

import com.badlogic.gdx.graphics.Color;

public class PatternsConstants {

    // Cantor Gasket
    public static final int CANTOR_STARTING_RECURSIONS = 3;
    public static final Color[] CANTOR_COLORS = {PASTEL_RED, PASTEL_GREEN, PASTEL_BLUE, PASTEL_YELLOW, PASTEL_ORANGE, PASTEL_PURPLE};

    // Dragon Curve
    public static final int DRAGON_RECURSIONS = 9;

    // Phyllotaxis
    public static final int PHYLLOTAXIS_CIRCLES = 1000;
    public static final float PHYLLOTAXIS_BALL_RADIUS = 9f;

    // Spiral
    public static final int DOT_COUNT = 180;
    public static final float DOT_SIZE = 35f;

    public static final float CENTERED_CENTER_DISTANCE = (SHOWCASE_SIZE - DOT_SIZE) / 4f;
    public static final float CENTERED_OWN_CENTER_DISTANCE = CENTERED_CENTER_DISTANCE;

    public static final float UNCENTERED_CENTER_DISTANCE = (SHOWCASE_SIZE - DOT_SIZE) * 0.35f;
    public static final float UNCENTERED_OWN_CENTER_DISTANCE = (SHOWCASE_SIZE - DOT_SIZE) * 0.15f;

    // Spirograph
    public static final float BALL_RADIUS = 10f;
    public static final float FADE_DURATION = 180f;
}