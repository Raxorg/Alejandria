package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;

public class CrossChunkSelectionConstants {

    public static final int GRID_DIMENSION = 4;
    public static final float GRID_X = 100f, GRID_Y = SHOWCASE_STRIPE_HEIGHT;
    public static final float GRID_SIZE = SHOWCASE_SIZE;

    public static final float CHUNK_SIZE = GRID_SIZE / GRID_DIMENSION;
    public static final int CHUNK_DIMENSION = 2;
    public static final float SPACING = 0f;
    public static final float CELL_SIZE = (CHUNK_SIZE - (SPACING * (CHUNK_DIMENSION - 1))) / CHUNK_DIMENSION;
}