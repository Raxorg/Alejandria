package com.epicness.alejandria.showcase.assets;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.ARROW_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.BALL_BEEP;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.CIRCLE_GLOW_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.CIRCLE_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.GLOW_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.GUN_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.INFO_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.PIXEL_FONT_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.STICKMAN_RUN_ATLAS;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.utils.AnimationUtils;

public class ShowcaseAssets extends Assets {

    // Animations
    private Sprite[] stickmanRunFrames;
    // Sprites
    private Sprite frames;
    private Sprite arrow, info;
    private Sprite gun;
    private Sprite circle, circleGlow;
    private Sprite glow;
    // Audio
    private Sound ballBeep;
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

        loadTexture(CIRCLE_PATH);
        loadTexture(CIRCLE_GLOW_PATH);

        loadTexture(GLOW_PATH);
        // Audio
        loadSound(BALL_BEEP);
        // Fonts
        loadFont(PIXEL_FONT_PATH);
    }

    @Override
    public void initializeAssets() {
        // Animations
        getTexture(STICKMAN_RUN_ATLAS).setFilter(Linear, Linear);
        stickmanRunFrames = AnimationUtils.split(getTexture(STICKMAN_RUN_ATLAS), 82, 110);
        // Sprites
        frames = getSprite(STICKMAN_RUN_ATLAS);
        arrow = getSprite(ARROW_PATH);
        info = getSprite(INFO_PATH);
        gun = getSprite(GUN_PATH);

        circle = new Sprite(getTexture(CIRCLE_PATH));
        circleGlow = new Sprite(getTexture(CIRCLE_GLOW_PATH));

        glow = new Sprite(getTexture(GLOW_PATH));
        // Audio
        ballBeep = getSound(BALL_BEEP);
        // Fonts
        bigPixelFont = getFont(PIXEL_FONT_PATH);
        bigPixelFont.getData().scale(3f);
    }

    // Animations
    public Sprite[] getStickmanRunFrames() {
        return stickmanRunFrames;
    }

    // Sprites
    public Sprite getFrames() {
        return frames;
    }

    public Sprite getArrow() {
        return arrow;
    }

    public Sprite getInfo() {
        return info;
    }

    public Sprite getGun() {
        return gun;
    }

    public Sprite getCircle() {
        return circle;
    }

    public Sprite getCircleGlow() {
        return circleGlow;
    }

    public Sprite getGlow() {
        return glow;
    }

    // Audio
    public Sound getBallBeep() {
        return ballBeep;
    }

    // Fonts
    public BitmapFont getBigPixelFont() {
        return bigPixelFont;
    }
}