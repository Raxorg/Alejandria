package com.epicness.alejandria.showcase.logic.modules;

import com.epicness.alejandria.showcase.logic.ShowcaseLogicHandler;
import com.epicness.alejandria.showcase.logic.input.ModuleInputAdapter;
import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
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

    public final D setupModule() {
        logic.handler(ModuleInputAdapter.class).setModuleClass((Class<? extends Module<?>>) getClass());
        logic.handler(ShowcaseInputHandler.class).setModuleInputHandler(
                logic.handler(ModuleInputAdapter.class)
        );
        return drawable = setup();
    }

    protected abstract D setup();

    public void update(float delta) {
    }

    public void mouseMoved(float x, float y) {
    }

    public void touchDown(float x, float y) {
    }

    public final void exitModule() {
        drawable = null;
        logic.handler(ShowcaseInputHandler.class).setModuleInputHandler(null);
        exit();
    }

    protected void exit() {
    }

    public String getTitle() {
        return title;
    }

    public String getInformation() {
        return information;
    }
}