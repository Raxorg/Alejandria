package com.epicness.alejandria.showcase.logic;

import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.G;
import static com.badlogic.gdx.Input.Keys.I;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;

import com.epicness.alejandria.showcase.stuff.ShowcaseStuff;
import com.epicness.fundamentals.input.InputHandler;

public class ShowcaseInputHandler extends InputHandler<ShowcaseLogic, ShowcaseStuff> {

    private Module<?> module;

    @Override
    public void mouseMoved(float x, float y) {
        logic.get(ShowcaseHandler.class).mouseMoved(x, y);

        if (module != null) module.mouseMoved(x, y);
    }

    @Override
    public void touchDown(float x, float y) {
        if (module != null) module.touchDown(x, y);
    }

    @Override
    public void touchDragged(float x, float y) {
        logic.get(ShowcaseHandler.class).mouseMoved(x, y);

        if (module != null) module.touchDragged(x, y);
    }

    @Override
    public void touchUp(float x, float y) {
        logic.get(ShowcaseHandler.class).touchUp(x, y);

        if (module != null) module.touchUp(x, y);
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
            case I:
                logic.get(ShowcaseHandler.class).toggleInformation();
                return;
            case G:
                logic.get(ShowcaseHandler.class).openGitHub();
                return;
            case D:
                logic.get(ShowcaseHandler.class).toggleDebug();
                break;
        }
        if (module != null) module.keyDown(keycode);
    }

    @Override
    public void keyUp(int keycode) {
        if (module != null) module.keyUp(keycode);
    }

    public void setModule(Module<?> module) {
        this.module = module;
    }
}