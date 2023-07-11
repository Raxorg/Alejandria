package com.epicness.fundamentals.initializer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.epicness.fundamentals.SharedResources;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class Initializer<A extends Assets, R extends Renderer<S>, S extends Stuff<A>> {

    protected final A assets;
    protected Logic logic;
    protected R renderer;
    protected S stuff;
    private boolean initialized;

    public Initializer(A assets, Logic logic, R renderer, S stuff) {
        this.assets = assets;
        this.logic = logic;
        this.renderer = renderer;
        this.stuff = stuff;
    }

    public final void initialize(SharedResources sharedResources) {
        SharedScreen screen = sharedResources.getScreen();

        logic.setStructure(
                (Game) Gdx.app.getApplicationListener(),
                sharedResources.getAssets(),
                sharedResources.getInput(),
                sharedResources.getLogic(),
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

    public void setInitialized() {
        initialized = true;
    }

    public boolean wasInitialized() {
        return initialized;
    }
}