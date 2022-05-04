package com.epicness.alejandria.showcase.assets;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.ARROW_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.GLOW_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.GUN_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.INFO_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.PIXEL_FONT_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.STICKMAN_RUN_ATLAS;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.utils.AnimationUtils;

public class ShowcaseAssets extends Assets {

    // Animations
    private Sprite[] stickmanRunFrames;
    // Sprites
    private Sprite arrow, info;
    private Sprite gun, glow;
    // Fonts
    private BitmapFont bigPixelFont;

    @Override
    public void queueAssetLoading() {
        // Animations
        loadTexture(STICKMAN_RUN_ATLAS);
        // Sprites
        loadTexture(ARROW_PATH);
        loadTexture(INFO_PATH);
        loadTexture(GUN_PATH);
        loadTexture(GLOW_PATH);
        // Fonts
        loadFont(PIXEL_FONT_PATH);
    }

    @Override
    public void initializeAssets() {
        // Animations
        getTexture(STICKMAN_RUN_ATLAS).setFilter(Linear, Linear);
        stickmanRunFrames = AnimationUtils.split(getTexture(STICKMAN_RUN_ATLAS), 82, 110);
        // Sprites
        arrow = new Sprite(getTexture(ARROW_PATH));
        info = new Sprite(getTexture(INFO_PATH));
        gun = new Sprite(getTexture(GUN_PATH));
        glow = new Sprite(getTexture(GLOW_PATH));
        // Fonts
        bigPixelFont = getFont(PIXEL_FONT_PATH);
        bigPixelFont.getData().scale(3f);
    }

    // Animations
    public Sprite[] getStickmanRunFrames() {
        return stickmanRunFrames;
    }

    // Sprites
    public Sprite getArrow() {
        return arrow;
    }

    public Sprite getInfo() {
        return info;
    }

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