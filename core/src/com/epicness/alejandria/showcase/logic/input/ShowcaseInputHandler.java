package com.epicness.alejandria.showcase.logic.input;

import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;

import com.epicness.alejandria.showcase.logic.ShowcaseHandler;
import com.epicness.fundamentals.input.InputHandler;

public class ShowcaseInputHandler extends InputHandler {

    private ModuleInput moduleInput;

    @Override
    public void mouseMoved(float x, float y) {
        ShowcaseHandler showcaseHandler = (ShowcaseHandler) logic.getHandler(ShowcaseHandler.class);
        showcaseHandler.mouseMoved(x, y);

        if (moduleInput == null) {
            return;
        }
        moduleInput.mouseMoved(x, y);
    }

    @Override
    public void touchDown(float x, float y) {
        ShowcaseHandler showcaseHandler = (ShowcaseHandler) logic.getHandler(ShowcaseHandler.class);
        showcaseHandler.touchDown(x, y);

        if (moduleInput == null) {
            return;
        }
        moduleInput.touchDown(x, y);
    }

    @Override
    public void keyDown(int keycode) {
        ShowcaseHandler showcaseHandler = (ShowcaseHandler) logic.getHandler(ShowcaseHandler.class);
        switch (keycode) {
            case LEFT:
                showcaseHandler.keyDown(true);
                return;
            case RIGHT:
                showcaseHandler.keyDown(false);
                return;
        }
        if (moduleInput == null) {
            return;
        }
        moduleInput.keyDown(keycode);
    }

    public void setModuleInputHandler(ModuleInput moduleInput) {
        this.moduleInput = moduleInput;
        if (moduleInput != null) {
            moduleInput.setStructure(null, logic, null, stuff);
        }
    }
}