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
        logic.get(ShowcaseHandler.class).mouseMoved(x, y);

        if (moduleInput != null) moduleInput.mouseMoved(x, y);
    }

    @Override
    public void touchDown(float x, float y) {
        if (moduleInput != null) moduleInput.touchDown(x, y);
    }

    @Override
    public void touchDragged(float x, float y) {
        logic.get(ShowcaseHandler.class).mouseMoved(x, y);

        if (moduleInput != null) moduleInput.touchDragged(x, y);
    }

    @Override
    public void touchUp(float x, float y) {
        logic.get(ShowcaseHandler.class).touchUp(x, y);

        if (moduleInput != null) moduleInput.touchUp(x, y);
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode) {
            case LEFT:
                logic.get(ShowcaseHandler.class).keyDown(true);
                return;
            case RIGHT:
                logic.get(ShowcaseHandler.class).keyDown(false);
                return;
        }
        if (moduleInput != null) moduleInput.keyDown(keycode);
    }

    @Override
    public void keyUp(int keycode) {
        if (moduleInput != null) moduleInput.keyUp(keycode);
    }

    public void setModuleInputHandler(ModuleInput<?> moduleInput) {
        this.moduleInput = moduleInput;
    }
}