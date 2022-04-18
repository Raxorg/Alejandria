package com.epicness.fundamentals.logic;

import com.badlogic.gdx.Game;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.assets.SharedAssets;
import com.epicness.fundamentals.input.SharedInput;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.stuff.SharedStuff;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class LogicHandler {

    protected Game game;
    protected SharedAssets sharedAssets;
    protected SharedInput input;
    protected SharedLogic sharedLogic;
    protected SharedScreen screen;
    protected SharedStuff sharedStuff;

    protected final void setSharedStructure(
            Game game,
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

    protected abstract void setStructure(Assets assets, Logic logic, Renderer renderer, Stuff stuff);
}