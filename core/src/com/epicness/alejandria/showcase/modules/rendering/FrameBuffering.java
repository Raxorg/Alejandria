package com.epicness.alejandria.showcase.modules.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.epicness.alejandria.showcase.logic.Module;

public class FrameBuffering extends Module<FrameBufferingDrawable> {

    public FrameBuffering() {
        super(
            "Frame Buffer Example",
            """
            Blue means normal rendering

            Red means we are using a frame buffer

            Space to toggle
            """
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