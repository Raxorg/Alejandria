package com.epicness.alejandria.showcase.assets;

import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.GLOW_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.GUN_PATH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.assets.Assets;

public class ShowcaseAssets extends Assets {

    // Sprites
    private Sprite gun, glow;

    @Override
    public void queueAssetLoading() {
        loadTexture(GUN_PATH);
        loadTexture(GLOW_PATH);
    }

    @Override
    public void initializeAssets() {
        gun = new Sprite(getTexture(GUN_PATH));
        glow = new Sprite(getTexture(GLOW_PATH));
    }

    // Sprites
    public Sprite getGun() {
        return gun;
    }

    public Sprite getGlow() {
        return glow;
    }
}