package com.epicness.fundamentals.assets;

import static com.epicness.fundamentals.assets.SharedAssetPaths.*;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SharedAssets extends Assets {
    private Sprite spritesAtlas;

    private Sprite circle;

    private Sprite dot;

    private Sprite glow;

    private Sprite libGDX;

    private Sprite square32;

    private Sprite square32Inverted;

    private Sprite triangle;

    private Sprite weirdShape;

    private Sound beep;

    private Sound shortLaser;

    private BitmapFont pixelFont;

    private BitmapFont timesSquare;

    private Sprite pixel;

    public SharedAssets() {
        super(ASSETS);
    }

    @Override
    public void initializeAssets() {
        spritesAtlas = new Sprite(get(SPRITES_ATLAS).getTextures().first());
        circle = get(SPRITES_ATLAS).createSprite("circle");
        dot = get(SPRITES_ATLAS).createSprite("dot");
        glow = get(SPRITES_ATLAS).createSprite("glow");
        libGDX = get(SPRITES_ATLAS).createSprite("libGDX");
        square32 = get(SPRITES_ATLAS).createSprite("square32");
        square32Inverted = get(SPRITES_ATLAS).createSprite("square32Inverted");
        triangle = get(SPRITES_ATLAS).createSprite("triangle");
        weirdShape = get(SPRITES_ATLAS).createSprite("weirdShape");
        beep = get(BEEP_SOUND);
        shortLaser = get(SHORTLASER_SOUND);
        pixelFont = get(PIXELFONT_FONT);
        timesSquare = get(TIMESSQUARE_FONT);
        pixel = get(PIXEL_SPRITE);
    }

    public Sprite getSpritesAtlas() {
        return spritesAtlas;
    }

    public Sprite getCircle() {
        return circle;
    }

    public Sprite getDot() {
        return dot;
    }

    public Sprite getGlow() {
        return glow;
    }

    public Sprite getLibGDX() {
        return libGDX;
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

    public Sound getBeep() {
        return beep;
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

    public Sprite getPixel() {
        return pixel;
    }
}