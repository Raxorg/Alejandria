package com.epicness.alejandria.showcase.constants;

import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

public class CrossChunkSelectionConstants {

    public static final int DIMENSION = 4;
    public static final float CHUNK_SIZE = CAMERA_WIDTH / DIMENSION;
    public static final int CHUNK_DIMENSION = 2;
    public static final float SPACING = 0f;
    public static final float CELL_SIZE = (CHUNK_SIZE - (SPACING * (CHUNK_DIMENSION - 1))) / CHUNK_DIMENSION;
}