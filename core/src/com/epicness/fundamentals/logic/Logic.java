package com.epicness.fundamentals.logic;

import com.badlogic.gdx.Game;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.assets.SharedAssets;
import com.epicness.fundamentals.input.SharedInput;
import com.epicness.fundamentals.stuff.SharedStuff;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class Logic {

    protected final SharedLogic sharedLogic;

    public Logic(SharedLogic sharedLogic) {
        this.sharedLogic = sharedLogic;
    }

    public abstract void initialLogic();

    public abstract void update(float delta);

    public void pause() {

    }

    public void setGame(Game game) {

    }

    public void setSharedAssets(SharedAssets sharedAssets) {

    }

    public void setAssets(Assets assets) {

    }

    public void setInput(SharedInput input) {

    }

    public void setScreen(SharedScreen screen) {

    }

    public void setSharedStuff(SharedStuff sharedStuff) {

    }

    public void setStuff(Stuff stuff) {

    }
}