package com.epicness.alejandria;

import com.badlogic.gdx.Game;
import com.epicness.alejandria.showcase.ShowcaseInitializer;
import com.epicness.alejandria.showcase.assets.ShowcaseAssets;
import com.epicness.fundamentals.SharedResources;

public class AlejandriaApp extends Game {

    @Override
    public void create() {
        ShowcaseAssets assets = new ShowcaseAssets();
        assets.queueAssetLoading();
        assets.finishLoading();
        assets.initializeAssets();
        new ShowcaseInitializer(assets).initialize(new SharedResources());
    }
}