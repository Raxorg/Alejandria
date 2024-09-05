package com.epicness.alejandria.showcase.constants;

import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

public class AdvancedSplitScreenConstants {

    public static final float MIN_VIEWPORT_SIZE = VIEWPORT_HALF_WIDTH;
    public static final float MAX_VIEWPORT_SIZE = VIEWPORT_WIDTH;
    public static final float MAX_DISTANCE = MAX_VIEWPORT_SIZE / 2f;

    public static final float GRID_SIZE = VIEWPORT_WIDTH * 2f;
    public static final int GRID_COLUMNS = 8, GRID_ROWS = 8;
    public static final float CELL_SIZE = GRID_SIZE / GRID_COLUMNS;

    public static final float DIVIDER_THICKNESS = 20f;

    public static final float PLAYER_RADIUS = 30f;
    public static final float PLAYER_SPEED = 600f;
}