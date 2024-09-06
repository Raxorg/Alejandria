package com.epicness.alejandria.showcase.constants;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

public class AStarConstants {

    // A*
    public static final int A_STAR_GRID_COLUMNS = 30;
    public static final float A_STAR_GRID_CELL_SIZE = VIEWPORT_WIDTH / A_STAR_GRID_COLUMNS;
    public static final int A_STAR_GRID_ROWS = (int) (SHOWCASE_SIZE / A_STAR_GRID_CELL_SIZE);

    // Alternative A*
    public static final int CM_A_STAR_GRID_COLUMNS = 10;
    public static final float CM_A_STAR_GRID_CELL_SIZE = VIEWPORT_WIDTH / CM_A_STAR_GRID_COLUMNS;
    public static final int CM_A_STAR_GRID_ROWS = (int) (SHOWCASE_SIZE / CM_A_STAR_GRID_CELL_SIZE);
    public static final int MOVE_STRAIGHT_COST = 10;
    public static final int MOVE_DIAGONALLY_COST = 14;
}