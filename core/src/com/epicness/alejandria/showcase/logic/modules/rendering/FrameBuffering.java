package com.epicness.alejandria.showcase.logic.modules.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.rendering.FrameBufferingDrawable;

public class FrameBuffering extends Module<FrameBufferingDrawable> {

    public FrameBuffering() {
        super(
                "Frame Buffer Example",
                "Red means we are using a frame buffer\n\nBlue means normal rendering\n\nSpace to toggle"
        );
    }

    @Override
    public FrameBufferingDrawable setup() {
        return new FrameBufferingDrawable(sharedAssets.getGlow());
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) drawable.toggleDrawDirect();
    }
}