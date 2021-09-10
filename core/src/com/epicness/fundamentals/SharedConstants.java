package com.epicness.fundamentals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class SharedConstants {

    // Camera
    public static final float RATIO = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
    public static final float CAMERA_HEIGHT = 1000;
    public static final float CAMERA_WIDTH = CAMERA_HEIGHT * RATIO;
    // Asset paths
    public static final String GLOW_PATH = "images/shared/glow.png";
    public static final String PIXEL_PATH = "images/shared/pixel.png";
    public static final String SQUARE_32_PATH = "images/shared/square32.png";
    public static final String SQUARE_32_INVERTED_PATH = "images/shared/square32Inverted.png";
    public static final String WEIRD_SHAPE_PATH = "images/shared/weirdShape.png";
    // Colors
    public static final Color TRANSPARENT = Color.BLACK.cpy().lerp(Color.CLEAR, 0.5f);
    public static final Color HALF_TRANSPARENT = Color.BLACK.cpy().lerp(Color.CLEAR, 0.75f);
    public static final Color DIRT = new Color(0xeea160ff);
    public static final Color LIGHT_DIRT = new Color(0xf4cca1ff);
    public static final Color GRASS = new Color(0x71aa34ff);
    public static final Color LIGHT_GRASS = new Color(0xb6d53cff);
    public static final Color DARK_GRASS = new Color(0x003000ff);
}