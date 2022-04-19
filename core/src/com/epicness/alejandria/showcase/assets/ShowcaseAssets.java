package com.epicness.alejandria.showcase.assets;

import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.GLOW_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.GUN_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.PIXEL_FONT_PATH;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.assets.Assets;

public class ShowcaseAssets extends Assets {

    // Sprites
    private Sprite gun, glow;
    // Fonts
    private BitmapFont bigPixelFont;

    @Override
    public void queueAssetLoading() {
        // Sprites
        loadTexture(GUN_PATH);
        loadTexture(GLOW_PATH);
        // Fonts
        loadFont(PIXEL_FONT_PATH);
    }

    @Override
    public void initializeAssets() {
        // Sprites
        gun = new Sprite(getTexture(GUN_PATH));
        glow = new Sprite(getTexture(GLOW_PATH));
        // Fonts
        bigPixelFont = getFont(PIXEL_FONT_PATH);
        bigPixelFont.getData().scale(3f);
    }

    // Sprites
    public Sprite getGun() {
        return gun;
    }

    public Sprite getGlow() {
        return glow;
    }

    // Fonts
    public BitmapFont getBigPixelFont() {
        return bigPixelFont;
    }
}