package com.epicness.alejandria.showcase.logic.modules;

import com.epicness.alejandria.showcase.logic.ShowcaseLogicHandler;
import com.epicness.alejandria.showcase.stuff.Drawable;

/**
 * The modules package is an exception to the conventional structuring of epicness games/apps
 **/
public abstract class Module extends ShowcaseLogicHandler {

    private final String title;
    private final String information;

    public Module(String title, String information) {
        this.title = title;
        this.information = information;
    }

    public abstract Drawable setup();

    public void update(float delta) {
    }

    public void exit() {
    }

    public String getTitle() {
        return title;
    }

    public String getInformation() {
        return information;
    }
}