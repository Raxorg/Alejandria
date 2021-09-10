package com.epicness.fundamentals.logic;

import com.epicness.fundamentals.assets.Assets;

public class AssetLoader {

    // Logic
    private Assets assets;
    private boolean assetsAssigned;

    protected void startLoadingAssets(Assets assetsToLoad) {
        if (assetsToLoad.areAssetsInitialized()) {
            return;
        }
        assets = assetsToLoad;
        assets.queueAssetLoading();
        assetsAssigned = true;
    }

    public void update() {
        if (!assetsAssigned) {
            return;
        }
        if (assets.areAssetsInitialized()) {
            return;
        }
        if (assets.loadAssets()) {
            assets.initializeAssets();
            assets.assetsInitialized();
        }
    }

    public Assets getAssets() {
        return assets;
    }
}