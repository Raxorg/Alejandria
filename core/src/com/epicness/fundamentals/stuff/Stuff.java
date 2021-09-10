package com.epicness.fundamentals.stuff;

import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.assets.SharedAssets;

public abstract class Stuff {

    // Structure
    protected SharedAssets sharedAssets;
    protected Assets assets;

    public abstract void initializeStuff();

    // Structure
    public void setSharedAssets(SharedAssets sharedAssets) {
        this.sharedAssets = sharedAssets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }
}