package com.epicness.fundamentals;

import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class Initializer {

    protected final Assets assets;
    protected Logic logic;
    protected Renderer renderer;
    protected Stuff stuff;
    private boolean initialized;

    public Initializer(Assets assets) {
        this.assets = assets;
    }

    public void initialize(SharedResources sharedResources) {
        SharedScreen screen = sharedResources.getScreen();

        logic.setAssets(assets);
        logic.setSharedAssets(sharedResources.getAssets());
        logic.setInput(sharedResources.getInput());
        logic.setScreen(screen);
        logic.setStuff(stuff);
        renderer.setScreen(screen);
        renderer.setStuff(stuff);
        screen.setLogic(logic);
        screen.setRenderer(renderer);
        stuff.setSharedAssets(sharedResources.getAssets());
        stuff.setAssets(assets);

        assets.queueAssetLoading();
        assets.loadAssets();
        assets.initializeAssets();
        renderer.setProjectionMatrix();
        stuff.initializeStuff();

        logic.initialLogic();
    }

    public final void fastInitialize(SharedResources sharedResources) {
        SharedScreen screen = sharedResources.getScreen();

        screen.setLogic(logic);
        screen.setRenderer(renderer);

        logic.initialLogic();
    }

    public Assets getAssets() {
        return assets;
    }

    public void initialized() {
        initialized = true;
    }

    public boolean wasInitialized() {
        return initialized;
    }
}