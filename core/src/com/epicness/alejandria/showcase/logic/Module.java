package com.epicness.alejandria.showcase.logic;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.GITHUB_ROOT;

import com.epicness.alejandria.showcase.logic.input.ModuleInputAdapter;
import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
import com.epicness.alejandria.showcase.stuff.Drawable;

/**
 * The modules package is an exception to the conventional package structure of epicness games/apps
 **/
public abstract class Module<D extends Drawable> extends ShowcaseLogicHandler {

    protected final String title;
    protected final String information;
    protected final String gitHubPath;
    protected D drawable;

    public Module(String title, String information) {
        this.title = title;
        this.information = information;
        gitHubPath = GITHUB_ROOT + getClass().getName().replace(".", "/") + ".java";
    }

    public final D setupModule() {
        logic.get(ModuleInputAdapter.class).setModuleClass((Class<? extends Module<?>>) getClass());
        logic.get(ShowcaseInputHandler.class).setModuleInputHandler(
                logic.get(ModuleInputAdapter.class)
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

    public void touchDragged(float x, float y) {
    }

    public void touchUp(float x, float y) {
    }

    public void keyDown(int keycode) {
    }

    public void keyUp(int keycode) {
    }

    public final void exitModule() {
        drawable = null;
        logic.get(ShowcaseInputHandler.class).setModuleInputHandler(null);
        exit();
    }

    protected void exit() {
    }
}