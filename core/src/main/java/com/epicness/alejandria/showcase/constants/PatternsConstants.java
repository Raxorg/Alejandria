package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;

public class PatternsConstants {

    // Cantor
    public static final int CANTOR_RECURSIONS = 5;

    // Phyllotaxis
    public static final int PHYLLOTAXIS_CIRCLES = 1000;

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