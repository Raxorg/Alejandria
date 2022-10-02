package com.epicness.alejandria.showcase.logic.input;

import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;

import com.epicness.alejandria.showcase.logic.ShowcaseHandler;
import com.epicness.alejandria.showcase.logic.ShowcaseLogic;
import com.epicness.alejandria.showcase.stuff.ShowcaseStuff;
import com.epicness.fundamentals.input.InputHandler;

public class ShowcaseInputHandler extends InputHandler<ShowcaseLogic, ShowcaseStuff> {

    private ModuleInput<?> moduleInput;

    @Override
    public void mouseMoved(float x, float y) {
        logic.handler(ShowcaseHandler.class).mouseMoved(x, y);

        if (moduleInput == null) {
            return;
        }
        moduleInput.mouseMoved(x, y);
    }

    @Override
    public void touchDown(float x, float y) {
        logic.handler(ShowcaseHandler.class).touchDown(x, y);

        if (moduleInput == null) {
            return;
        }
        moduleInput.touchDown(x, y);
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode) {
            case LEFT:
                logic.handler(ShowcaseHandler.class).keyDown(true);
                return;
            case RIGHT:
                logic.handler(ShowcaseHandler.class).keyDown(false);
                return;
        }
        if (moduleInput == null) {
            return;
        }
        moduleInput.keyDown(keycode);
    }

    public void setModuleInputHandler(ModuleInput<?> moduleInput) {
        this.moduleInput = moduleInput;
    }
}