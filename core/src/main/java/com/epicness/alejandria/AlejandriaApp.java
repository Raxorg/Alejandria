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
        // TODO: 2/29/2024 Show FPS (1/Delta or Gdx.graphics.getFramesPerSecond() and Delta Time when debug enabled
        // TODO: 3/9/2024 Add module category labels
    }
}