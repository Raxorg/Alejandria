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
        module = logic.handler(setup());
    }

    protected abstract Class<M> setup();
}