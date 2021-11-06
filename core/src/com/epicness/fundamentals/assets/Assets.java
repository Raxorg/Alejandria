package com.epicness.fundamentals.assets;

import com.badlogic.gdx.assets.AssetManager;

public abstract class Assets {

    protected final AssetManager assetManager;
    private boolean assetsInitialized = false;

    public Assets() {
        assetManager = new AssetManager();
    }

    public abstract void queueAssetLoading();

    /* Default recommended way of loading assets -> async */
    public final boolean loadAssets() {
        return assetManager.update();
    }

    /* Obstructing way of loading assets -> blocks until finished */
    public final void finishLoading() {
        assetManager.finishLoading();
    }

    public abstract void initializeAssets();

    public void assetsInitialized() {
        assetsInitialized = true;
    }

    public boolean areAssetsInitialized() {
        return assetsInitialized;
    }
}