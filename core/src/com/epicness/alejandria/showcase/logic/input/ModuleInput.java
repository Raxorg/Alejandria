package com.epicness.alejandria.showcase.logic.input;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.math.MathUtils;
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

    @Override
    public final void mouseMoved(float x, float y) {
        x = MathUtils.map(150f, 150f + SHOWCASE_SIZE, 0f, CAMERA_WIDTH, x);
        y = MathUtils.map(150f, 150f + SHOWCASE_SIZE, 0f, CAMERA_HEIGHT, y);
        module.mouseMoved(x, y);
    }

    @Override
    public final void touchDown(float x, float y) {
        x = MathUtils.map(150f, 150f + SHOWCASE_SIZE, 0f, CAMERA_WIDTH, x);
        y = MathUtils.map(150f, 150f + SHOWCASE_SIZE, 0f, CAMERA_HEIGHT, y);
        module.touchDown(x, y);
    }

    protected abstract Class<M> setup();
}