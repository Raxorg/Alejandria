package com.epicness.alejandria.showcase.logic.modules;

import com.epicness.alejandria.showcase.logic.ShowcaseLogicHandler;
import com.epicness.alejandria.showcase.stuff.Drawable;

/**
 * The modules package is an exception to the conventional package structure of epicness games/apps
 **/
public abstract class Module<D extends Drawable> extends ShowcaseLogicHandler {

    private final String title;
    private final String information;
    protected D drawable;

    public Module(String title, String information) {
        this.title = title;
        this.information = information;
    }

    public abstract D setup();

    public void update(float delta) {
    }

    public final void exitModule() {
        drawable = null;
        exit();
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