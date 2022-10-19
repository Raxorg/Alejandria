package com.epicness.alejandria.showcase.logic.input;

import com.epicness.alejandria.showcase.logic.ShowcaseLogic;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.ShowcaseStuff;
import com.epicness.fundamentals.input.InputHandler;

public abstract class ModuleInput<M extends Module<?>> extends InputHandler<ShowcaseLogic, ShowcaseStuff> {

    protected M module;

    @Override
    public void init() {
        super.init();
        module = logic.get(setup());
    }

    @Override
    public final void mouseMoved(float x, float y) {
        module.mouseMoved(x, y);
    }

    @Override
    public final void touchDown(float x, float y) {
        module.touchDown(x, y);
    }

    protected abstract Class<M> setup();
}