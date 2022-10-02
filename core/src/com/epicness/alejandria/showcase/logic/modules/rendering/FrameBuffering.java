package com.epicness.alejandria.showcase.logic.modules.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.rendering.FrameBufferingDrawable;

public class FrameBuffering extends Module<FrameBufferingDrawable> {

    public FrameBuffering() {
        super("Frame Buffer Example", "Red means we are using a frame buffer, blue means normal rendering");
    }

    @Override
    public FrameBufferingDrawable setup() {
        return drawable = new FrameBufferingDrawable(
                sharedAssets.getWeirdShape(),
                stuff.getShowcase().getFrameBuffer()
        );
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) drawable.toggleDrawDirect();
    }
}