package com.epicness.fundamentals.stuff;

import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.assets.SharedAssets;

public abstract class Stuff<A extends Assets> {

    // Structure
    protected SharedAssets sharedAssets;
    protected A assets;

    public abstract void initializeStuff();

    // Structure
    public void setSharedAssets(SharedAssets sharedAssets) {
        this.sharedAssets = sharedAssets;
    }

    public void setAssets(A assets) {
        this.assets = assets;
    }
}