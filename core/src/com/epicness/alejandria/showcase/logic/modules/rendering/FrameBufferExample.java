package com.epicness.alejandria.showcase.logic.modules.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.rendering.FrameBufferExampleDrawable;

public class FrameBufferExample extends Module {

    private FrameBufferExampleDrawable drawable;

    @Override
    public void setup() {
        drawable = new FrameBufferExampleDrawable();
        stuff.getShowcase().setDrawable(drawable);
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) drawable.toggleDrawDirect();
    }

    @Override
    public void exit() {
        drawable = null;
    }
}