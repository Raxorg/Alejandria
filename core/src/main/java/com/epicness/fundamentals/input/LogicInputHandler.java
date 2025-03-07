package com.epicness.fundamentals.input;

import com.badlogic.gdx.Game;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.logic.LogicHandler;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.stuff.Stuff;

/**
 * Logic Handler which can also handle input
 */
public abstract class LogicInputHandler<G extends Game, A extends Assets, L extends Logic, R extends Renderer<S>, S extends Stuff<A>>
    extends LogicHandler<G, A, L, R, S> {

    public final void register() {
        input.addInputHandler(this);
    }

    protected void consumeInput() {
        input.consumeInput();
    }

    // Input related to the static camera
    public void mouseMoved(float x, float y) {
    }

    public void scrolled(float amountX, float amountY) {
    }

    public void touchDown(float x, float y, int button) {
    }

    public void touchDragged(float x, float y) {
    }

    public void touchUp(float x, float y, int button) {
    }

    public void touchCancelled(float x, float y) {
    }

    // Input related to the dynamic camera
    public void mouseMovedDynamic(float x, float y) {
    }

    public void touchDownDynamic(float x, float y, int button) {
    }

    public void touchDraggedDynamic(float x, float y) {
    }

    public void touchUpDynamic(float x, float y, int button) {
    }

    public void touchCancelledDynamic(float x, float y) {
    }

    // Camera-agnostic input
    public void keyDown(int keycode) {
    }

    public void keyUp(int keycode) {
    }

    public void keyTyped(char character) {
    }
}