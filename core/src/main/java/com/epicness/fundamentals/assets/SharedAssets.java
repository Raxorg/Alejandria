package com.epicness.fundamentals.assets;

import static com.epicness.fundamentals.assets.SharedAssetPaths.*;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SharedAssets extends Assets {
    private Sound shortLaser;

    private BitmapFont pixelFont;

    private BitmapFont timesSquare;

    private Sprite circle;

    private Sprite glow;

    private Sprite libGDX;

    private Sprite pixel;

    private Sprite square32;

    private Sprite square32Inverted;

    private Sprite triangle;

    private Sprite weirdShape;

    public SharedAssets() {
        super(ASSETS);
    }

    @Override
    public void initializeAssets() {
        shortLaser = get(SHORTLASER_SOUND);
        pixelFont = get(PIXELFONT_FONT);
        timesSquare = get(TIMESSQUARE_FONT);
        circle = get(CIRCLE_SPRITE);
        glow = get(GLOW_SPRITE);
        libGDX = get(LIBGDX_SPRITE);
        pixel = get(PIXEL_SPRITE);
        square32 = get(SQUARE32_SPRITE);
        square32Inverted = get(SQUARE32INVERTED_SPRITE);
        triangle = get(TRIANGLE_SPRITE);
        weirdShape = get(WEIRDSHAPE_SPRITE);
    }

    public Sound getShortLaser() {
        return shortLaser;
    }

    public BitmapFont getPixelFont() {
        return pixelFont;
    }

    public BitmapFont getTimesSquare() {
        return timesSquare;
    }

    public Sprite getCircle() {
        return circle;
    }

    public Sprite getGlow() {
        return glow;
    }

    public Sprite getLibGDX() {
        return libGDX;
    }

    public Sprite getPixel() {
        return pixel;
    }

    public Sprite getSquare32() {
        return square32;
    }

    public Sprite getSquare32Inverted() {
        return square32Inverted;
    }

    public Sprite getTriangle() {
        return triangle;
    }

    public Sprite getWeirdShape() {
        return weirdShape;
    }
}