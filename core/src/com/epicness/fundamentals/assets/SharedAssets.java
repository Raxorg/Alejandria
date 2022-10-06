package com.epicness.fundamentals.assets;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
import static com.epicness.fundamentals.SharedConstants.CIRCLE_PATH;
import static com.epicness.fundamentals.SharedConstants.EXPLOSION_PATH;
import static com.epicness.fundamentals.SharedConstants.GLOW_PATH;
import static com.epicness.fundamentals.SharedConstants.PIXEL_FONT_PATH;
import static com.epicness.fundamentals.SharedConstants.PIXEL_PATH;
import static com.epicness.fundamentals.SharedConstants.SQUARE_32_INVERTED_PATH;
import static com.epicness.fundamentals.SharedConstants.SQUARE_32_PATH;
import static com.epicness.fundamentals.SharedConstants.TIMES_SQUARE_PATH;
import static com.epicness.fundamentals.SharedConstants.TRIANGLE_PATH;
import static com.epicness.fundamentals.SharedConstants.WEIRD_SHAPE_PATH;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SharedAssets extends Assets {

    // Audio
    private Sound explosionSound;
    // Fonts
    private BitmapFont pixelFont, timesSquare;
    // Sprites
    private Sprite circle, triangle;
    private Sprite glow;
    private Sprite pixel;
    private Sprite square, squareInverted;
    private Sprite weirdShape;

    @Override
    public void queueAssetLoading() {
        // Audio
        loadSound(EXPLOSION_PATH);
        // Fonts
        loadFont(PIXEL_FONT_PATH);
        loadFont(TIMES_SQUARE_PATH);
        // Sprites
        loadTexture(CIRCLE_PATH);
        loadTexture(TRIANGLE_PATH);
        loadTexture(GLOW_PATH);
        loadTexture(PIXEL_PATH);
        loadTexture(SQUARE_32_PATH);
        loadTexture(SQUARE_32_INVERTED_PATH);
        loadTexture(WEIRD_SHAPE_PATH);
    }

    @Override
    public void initializeAssets() {
        // Audio
        explosionSound = getSound(EXPLOSION_PATH);
        // Fonts
        pixelFont = getFont(PIXEL_FONT_PATH);
        timesSquare = getFont(TIMES_SQUARE_PATH);
        // Sprites
        circle = getSprite(CIRCLE_PATH, Linear);
        triangle = getSprite(TRIANGLE_PATH, Linear);
        glow = getSprite(GLOW_PATH);
        pixel = getSprite(PIXEL_PATH);
        square = getSprite(SQUARE_32_PATH);
        squareInverted = getSprite(SQUARE_32_INVERTED_PATH);
        weirdShape = getSprite(WEIRD_SHAPE_PATH);
    }

    // Audio
    public Sound getExplosionSound() {
        return explosionSound;
    }

    // Fonts
    public BitmapFont getPixelFont() {
        return pixelFont;
    }

    public BitmapFont getTimesSquare() {
        return timesSquare;
    }

    // Sprites
    public Sprite getCircle() {
        return circle;
    }

    public Sprite getTriangle() {
        return triangle;
    }

    public Sprite getGlow() {
        return glow;
    }

    public Sprite getPixel() {
        return pixel;
    }

    public Sprite getSquare() {
        return square;
    }

    public Sprite getSquareInverted() {
        return squareInverted;
    }

    public Sprite getWeirdShape() {
        return weirdShape;
    }
}