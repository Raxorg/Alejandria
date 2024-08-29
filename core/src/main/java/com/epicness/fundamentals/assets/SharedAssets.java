package com.epicness.fundamentals.assets;

import static com.epicness.fundamentals.assets.SharedAssetPaths.*;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SharedAssets extends Assets {
    private Sprite spritesLinearAtlas;

    private Sprite circle;

    private Sprite dot;

    private Sprite glow;

    private Sprite libGDX;

    private Sprite triangle;

    private Sprite weirdShape;

    private Sprite spritesNearestAtlas;

    private Sprite pixel;

    private Sprite square32;

    private Sprite square32Inverted;

    private Sound beep;

    private Sound shortLaser;

    private BitmapFont pixelFont;

    private BitmapFont timesSquare;

    public SharedAssets() {
        super(ASSETS);
    }

    @Override
    public void initializeAssets() {
        spritesLinearAtlas = new Sprite(get(SPRITESLINEAR_ATLAS).getTextures().first());
        circle = get(SPRITESLINEAR_ATLAS).createSprite("circle");
        dot = get(SPRITESLINEAR_ATLAS).createSprite("dot");
        glow = get(SPRITESLINEAR_ATLAS).createSprite("glow");
        libGDX = get(SPRITESLINEAR_ATLAS).createSprite("libGDX");
        triangle = get(SPRITESLINEAR_ATLAS).createSprite("triangle");
        weirdShape = get(SPRITESLINEAR_ATLAS).createSprite("weirdShape");
        spritesNearestAtlas = new Sprite(get(SPRITESNEAREST_ATLAS).getTextures().first());
        pixel = get(SPRITESNEAREST_ATLAS).createSprite("pixel");
        square32 = get(SPRITESNEAREST_ATLAS).createSprite("square32");
        square32Inverted = get(SPRITESNEAREST_ATLAS).createSprite("square32Inverted");
        beep = get(BEEP_SOUND);
        shortLaser = get(SHORTLASER_SOUND);
        pixelFont = get(PIXELFONT_FONT);
        timesSquare = get(TIMESSQUARE_FONT);
    }

    public Sprite getSpritesLinearAtlas() {
        return spritesLinearAtlas;
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

    public Sprite getTriangle() {
        return triangle;
    }

    public Sprite getWeirdShape() {
        return weirdShape;
    }

    public Sprite getSpritesNearestAtlas() {
        return spritesNearestAtlas;
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
}