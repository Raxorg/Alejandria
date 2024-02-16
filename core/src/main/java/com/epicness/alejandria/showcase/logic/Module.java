package com.epicness.alejandria.showcase.logic;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.GITHUB_ROOT;

import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;

/**
 * The modules package is an exception to the conventional package structure of epicness games/apps
 **/
public abstract class Module<D extends ModuleDrawable> extends ShowcaseLogicHandler {

    protected final String title;
    protected final String information;
    protected final String gitHubPath;
    protected D drawable;

    public Module(String title, String information) {
        this.title = title;
        this.information = information;
        gitHubPath = GITHUB_ROOT + getClass().getName().replace(".", "/") + ".java";
    }

    @Override
    protected void init() {
    }

    public final D setupModule() {
        input.addInputHandler(this);
        return drawable = setup();
    }

    protected abstract D setup();

    public void update(float delta) {
    }

    public final void exitModule() {
        drawable = null;
        input.removeInputHandler(this);
        exit();
    }

    protected void exit() {
    }
}