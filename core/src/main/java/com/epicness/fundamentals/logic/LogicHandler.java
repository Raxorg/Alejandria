package com.epicness.fundamentals.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.assets.SharedAssets;
import com.epicness.fundamentals.input.SharedInput;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.stuff.SharedStuff;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class LogicHandler<G extends Game, A extends Assets, L extends Logic, R extends Renderer<S>, S extends Stuff<A>> {

    protected G game;
    protected SharedAssets sharedAssets;
    protected SharedInput input;
    protected SharedLogic sharedLogic;
    protected SharedScreen screen;
    protected SharedStuff sharedStuff;
    protected A assets;
    protected L logic;
    protected R renderer;
    protected S stuff;

    protected abstract void init();

    public final void update() {
        update(Gdx.graphics.getDeltaTime());
    }

    protected void update(float delta) {
    }

    public void resize(int width, int height) {
    }

    protected final void setSharedStructure(
        G game,
        SharedAssets assets,
        SharedInput input,
        SharedLogic sharedLogic,
        SharedScreen screen,
        SharedStuff sharedStuff
    ) {
        this.game = game;
        this.sharedAssets = assets;
        this.sharedLogic = sharedLogic;
        this.input = input;
        this.screen = screen;
        this.sharedStuff = sharedStuff;
    }

    protected final void setStructure(A assets, L logic, R renderer, S stuff) {
        this.assets = assets;
        this.logic = logic;
        this.renderer = renderer;
        this.stuff = stuff;
    }

    protected final <H extends LogicHandler<?, ?, ?, ?, ?>> H get(Class<H> handlerClass) {
        return logic.get(handlerClass);
    }
}