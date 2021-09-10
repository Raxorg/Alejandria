package com.epicness.fundamentals.logic;

import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.assets.SharedAssets;
import com.epicness.fundamentals.input.SharedInput;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class Logic {

    protected SharedLogic sharedLogic;

    public Logic(SharedLogic sharedLogic) {
        this.sharedLogic = sharedLogic;
    }

    public abstract void initialLogic();

    public abstract void update(float delta);

    public void setAssets(Assets assets) {

    }

    public void setSharedAssets(SharedAssets sharedAssets) {

    }

    public void setInput(SharedInput input) {

    }

    public void setScreen(SharedScreen screen) {

    }

    public void setStuff(Stuff stuff) {

    }
}