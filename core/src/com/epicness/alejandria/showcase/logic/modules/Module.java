package com.epicness.alejandria.showcase.logic.modules;

import com.epicness.alejandria.showcase.logic.ShowcaseLogicHandler;
import com.epicness.alejandria.showcase.stuff.Drawable;

/**
 * The modules package is an exception to the conventional structuring of epicness games/apps
 **/
public abstract class Module extends ShowcaseLogicHandler {

    private final String title;

    public Module(String title) {
        this.title = title;
    }

    public abstract Drawable setup();

    public void update(float delta) {

    }

    public void exit() {

    }

    public String getTitle() {
        return title;
    }
}