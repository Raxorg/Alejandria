package com.epicness.fundamentals;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

        logic.setStructure(
                (Game) Gdx.app.getApplicationListener(),
                sharedResources.getAssets(),
                sharedResources.getInput(),
                screen,
                sharedResources.getStuff(),
                assets,
                renderer,
                stuff
        );
        renderer.setScreen(screen);
        renderer.setSharedStuff(sharedResources.getStuff());
        renderer.setStuff(stuff);
        screen.setLogic(logic);
        screen.setRenderer(renderer);
        stuff.setSharedAssets(sharedResources.getAssets());
        stuff.setAssets(assets);

        renderer.useStaticCamera();
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