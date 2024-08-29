package com.epicness.fundamentals.logic.handlers;

import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.logic.CompletionListener;

import java.util.List;

public class AssetLoader {

    private List<Assets> pendingAssets;
    private CompletionListener listener;

    protected void beginLoading(List<Assets> assetsToLoad, CompletionListener completionListener) {
        pendingAssets = assetsToLoad;
        for (int i = 0; i < pendingAssets.size(); i++) {
            Assets assets = pendingAssets.get(i);
            if (assets.areAssetsInitialized()) {
                System.out.println(assets.getClass().getSimpleName() + " previously initialized");
                continue;
            }
            assets.queueAssetLoading();
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
                assets.initAssets();
            }
            return;
        }
        pendingAssets = null;
        listener.onComplete();
    }
}