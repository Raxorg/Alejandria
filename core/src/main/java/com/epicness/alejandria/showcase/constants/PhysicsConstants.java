package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_BUTTON_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

public class PhysicsConstants {

    // Ball Physics
    public static final float GRAVITY = -30f;
    public static final float PHYSICS_SCALE_FACTOR = 20f;

    public static final float CIRCLE_RADIUS = 20f;
    public static final float PHYSICS_CIRCLE_RADIUS = CIRCLE_RADIUS / PHYSICS_SCALE_FACTOR;

    public static final float FLOOR_X1 = SHOWCASE_BUTTON_SIZE;
    public static final float FLOOR_Y1 = SHOWCASE_STRIPE_HEIGHT * 2f;
    public static final float FLOOR_X2 = CAMERA_WIDTH - SHOWCASE_BUTTON_SIZE;
    public static final float FLOOR_Y2 = SHOWCASE_STRIPE_HEIGHT * 2f;
    public static final float PHYSICS_FLOOR_X1 = FLOOR_X1 / PHYSICS_SCALE_FACTOR;
    public static final float PHYSICS_FLOOR_Y1 = FLOOR_Y1 / PHYSICS_SCALE_FACTOR;
    public static final float PHYSICS_FLOOR_X2 = FLOOR_X2 / PHYSICS_SCALE_FACTOR;
    public static final float PHYSICS_FLOOR_Y2 = FLOOR_Y2 / PHYSICS_SCALE_FACTOR;
}