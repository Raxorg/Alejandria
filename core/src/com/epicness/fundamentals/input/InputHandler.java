package com.epicness.fundamentals.input;

import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class InputHandler {

    // Structure
    private SharedInput input;
    protected Logic logic;
    protected Stuff stuff;

    public void setupInput() {
        input.setInputHandler(this);
        input.setEnabled(true);
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

    // Structure
    public void setInput(SharedInput input) {
        this.input = input;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public void setStuff(Stuff stuff) {
        this.stuff = stuff;
    }
}