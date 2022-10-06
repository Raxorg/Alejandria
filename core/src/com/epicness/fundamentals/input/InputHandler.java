package com.epicness.fundamentals.input;

import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.logic.LogicHandler;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class InputHandler<L extends Logic, S extends Stuff<?>> extends LogicHandler<Assets, L, Renderer<?>, S> {

    @Override
    public void init() {
        input.setInputHandler(this);
        input.setEnabled(true);
    }

    // Input related to the static camera
    public void mouseMoved(float x, float y) {

    }

    public void touchDown(float x, float y) {

    }

    public void touchDragged(float x, float y) {

    }

    public void touchUp(float x, float y) {

    }

    // Input related to the dynamic camera
    public void mouseMovedDynamic(float x, float y) {

    }

    public void touchDownDynamic(float x, float y) {

    }

    public void touchDraggedDynamic(float x, float y) {

    }

    public void touchUpDynamic(float x, float y) {

    }

    // Camera-agnostic input
    public void keyDown(int keycode) {

    }

    public void keyUp(int keycode) {

    }
}