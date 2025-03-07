package com.epicness.alejandria.showcase.constants;

import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;

public class MaskingConstants {

    // Alpha Masking
    public static final float PLUS_X = VIEWPORT_HALF_WIDTH;
    public static final float PLUS_Y = VIEWPORT_HEIGHT * 0.75f;
    public static final float EQUALS_X = VIEWPORT_HALF_WIDTH;
    public static final float EQUALS_Y = VIEWPORT_HALF_HEIGHT;
    public static final float MASKED_SPRITE_X = VIEWPORT_HALF_WIDTH;
    public static final float MASKED_SPRITE_Y = VIEWPORT_HEIGHT * 0.25f;
    public static final float MASK_X = MASKED_SPRITE_X;
    public static final float MASK_Y = MASKED_SPRITE_Y;

    // Layered Masking
    public static final float LAYERED_CIRCLE_RADIUS = VIEWPORT_HALF_HEIGHT * 0.5f;
    public static final float SHAPE_SIZE = 100f;
    public static final int GRID_COLUMNS = 10, GRID_ROWS = 10;

    // Shape Renderer Masking
    public static final float MASKED_CIRCLE_RADIUS = 200f;
    public static final float CIRCLE_MASK_RADIUS = 100f;
}