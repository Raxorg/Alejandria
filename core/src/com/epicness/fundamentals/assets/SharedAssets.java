package com.epicness.fundamentals.assets;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.epicness.fundamentals.SharedConstants.EXPLOSION_PATH;
import static com.epicness.fundamentals.SharedConstants.GLOW_PATH;
import static com.epicness.fundamentals.SharedConstants.PIXEL_FONT_PATH;
import static com.epicness.fundamentals.SharedConstants.PIXEL_PATH;
import static com.epicness.fundamentals.SharedConstants.SQUARE_32_INVERTED_PATH;
import static com.epicness.fundamentals.SharedConstants.SQUARE_32_PATH;
import static com.epicness.fundamentals.SharedConstants.WEIRD_SHAPE_PATH;

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
        assetManager.load(EXPLOSION_PATH, Sound.class);
        // Fonts
        assetManager.load(PIXEL_FONT_PATH, BitmapFont.class);
        // Sprites
        assetManager.load(GLOW_PATH, Texture.class);
        assetManager.load(PIXEL_PATH, Texture.class);
        assetManager.load(SQUARE_32_PATH, Texture.class);
        assetManager.load(SQUARE_32_INVERTED_PATH, Texture.class);
        assetManager.load(WEIRD_SHAPE_PATH, Texture.class);
    }

    @Override
    public void initializeAssets() {
        // Audio
        explosionSound = assetManager.get(EXPLOSION_PATH, Sound.class);
        // Fonts
        pixelFont = assetManager.get(PIXEL_FONT_PATH, BitmapFont.class);
        // Sprites
        glow = new Sprite(assetManager.get(GLOW_PATH, Texture.class));
        pixel = new Sprite(assetManager.get(PIXEL_PATH, Texture.class));
        square = new Sprite(assetManager.get(SQUARE_32_PATH, Texture.class));
        squareInverted = new Sprite(assetManager.get(SQUARE_32_INVERTED_PATH, Texture.class));
        weirdShape = new Sprite(assetManager.get(WEIRD_SHAPE_PATH, Texture.class));
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