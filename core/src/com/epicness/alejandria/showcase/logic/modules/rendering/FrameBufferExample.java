package com.epicness.alejandria.showcase.logic.modules.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.rendering.FrameBufferExampleDrawable;

public class FrameBufferExample extends Module {

    private FrameBufferExampleDrawable drawable;

    public FrameBufferExample() {
        super("Frame Buffer Example");
    }

    @Override
    public Drawable setup() {
        return drawable = new FrameBufferExampleDrawable();
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