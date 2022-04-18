package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;

public class AdvancedSplitScreenConstants {

    public static final float MIN_VIEWPORT_SIZE = INITIAL_WINDOW_SIZE / 2f;
    public static final float MAX_VIEWPORT_SIZE = INITIAL_WINDOW_SIZE;
    public static final float MAX_DISTANCE = MAX_VIEWPORT_SIZE / 2f;

    public static final float GRID_SIZE = INITIAL_WINDOW_SIZE * 2f;
    public static final int GRID_COLUMNS = 20, GRID_ROWS = 20;
    public static final float CELL_SIZE = GRID_SIZE / GRID_COLUMNS;

    public static final float PLAYER_RADIUS = 20;
}