package com.epicness.fundamentals.logic;

import com.epicness.fundamentals.stuff.SharedStuff;

public class BackgroundHandler {

    // Structure
    private SharedStuff stuff;

    public void update(float delta) {
        stuff.getAnimatedBackground().update(delta);
    }

    public void setStuff(SharedStuff stuff) {
        this.stuff = stuff;
    }
}