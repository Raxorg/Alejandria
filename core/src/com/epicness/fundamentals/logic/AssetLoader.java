package com.epicness.fundamentals.logic;

import com.epicness.fundamentals.assets.Assets;

import java.util.List;

public class AssetLoader {

    private List<Assets> pendingAssets;
    private CompletionListener listener;

    protected void beginLoading(List<Assets> assetsToLoad, CompletionListener completionListener) {
        pendingAssets = assetsToLoad;
        for (int i = 0; i < pendingAssets.size(); i++) {
            if (pendingAssets.get(i).areAssetsInitialized()) continue;
            pendingAssets.get(i).queueAssetLoading();
        }
        listener = completionListener;
    }

    protected void update() {
        if (pendingAssets == null) {
            return;
        }

        for (int i = 0; i < pendingAssets.size(); i++) {
            Assets assets = pendingAssets.get(i);
            if (assets.areAssetsInitialized()) {
                continue;
            }
            if (assets.loadAssets()) {
                assets.initializeAssets();
            }
            return;
        }
        pendingAssets = null;
        listener.onComplete();
    }
}