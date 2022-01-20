package com.epicness.fundamentals.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public abstract class Assets {

    private final AssetManager assetManager;
    private boolean assetsInitialized = false;

    public Assets() {
        assetManager = new AssetManager();
    }

    public abstract void queueAssetLoading();

    /* Default recommended way of loading assets -> async */
    public final boolean loadAssets() {
        return assetManager.update();
    }

    /* Obstructing way of loading assets -> blocks rendering until finished */
    public final void finishLoading() {
        assetManager.finishLoading();
    }

    public abstract void initializeAssets();

    public final void assetsInitialized() {
        assetsInitialized = true;
    }

    public final boolean areAssetsInitialized() {
        return assetsInitialized;
    }

    // Loading
    protected final void loadTexture(String path) {
        assetManager.load(path, Texture.class);
    }

    protected final void loadMusic(String path) {
        assetManager.load(path, Music.class);
    }

    protected final void loadSound(String path) {
        assetManager.load(path, Sound.class);
    }

    protected final void loadFont(String path) {
        assetManager.load(path, BitmapFont.class);
    }

    // Getting
    protected final Texture getTexture(String path) {
        return assetManager.get(path, Texture.class);
    }

    protected final Music getMusic(String path) {
        return assetManager.get(path, Music.class);
    }

    protected final Sound getSound(String path) {
        return assetManager.get(path, Sound.class);
    }

    protected final BitmapFont getFont(String path) {
        return assetManager.get(path, BitmapFont.class);
    }
}