package com.epicness.alejandria.showcase.logic.modules;

import com.epicness.alejandria.showcase.logic.ShowcaseLogicHandler;

/**
 * The modules package is an exception to the conventional structuring of epicness games/apps
 **/
public abstract class Module extends ShowcaseLogicHandler {

    public abstract void setup();

    public void update(float delta) {

    }

    public void exit() {

    }
}