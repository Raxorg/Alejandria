package com.epicness.fundamentals.assets;

import static com.epicness.fundamentals.SharedConstants.EXPLOSION_PATH;
import static com.epicness.fundamentals.SharedConstants.GLOW_PATH;
import static com.epicness.fundamentals.SharedConstants.PIXEL_FONT_PATH;
import static com.epicness.fundamentals.SharedConstants.PIXEL_PATH;
import static com.epicness.fundamentals.SharedConstants.SQUARE_32_INVERTED_PATH;
import static com.epicness.fundamentals.SharedConstants.SQUARE_32_PATH;
import static com.epicness.fundamentals.SharedConstants.WEIRD_SHAPE_PATH;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SharedAssets extends Assets {

    // Audio
    private Sound explosionSound;
    // Fonts
    private BitmapFont pixelFont;
    // Sprites
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
        // Sprites
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
        // Sprites
        glow = new Sprite(getTexture(GLOW_PATH));
        pixel = new Sprite(getTexture(PIXEL_PATH));
        square = new Sprite(getTexture(SQUARE_32_PATH));
        squareInverted = new Sprite(getTexture(SQUARE_32_INVERTED_PATH));
        weirdShape = new Sprite(getTexture(WEIRD_SHAPE_PATH));
    }

    // Audio
    public Sound getExplosionSound() {
        return explosionSound;
    }

    // Fonts
    public BitmapFont getPixelFont() {
        return pixelFont;
    }

    // Sprites
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