package com.epicness.alejandria.showcase.modules.rendering;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.epicness.alejandria.showcase.logic.Module;

public class FrameBufferExample extends Module<FrameBufferExampleDrawable> {

    public FrameBufferExample() {
        super(
            "Frame Buffer Example",
            "Blue means normal rendering\n\n" +
                "Red means we are using a frame buffer\n\n" +
                "Space to toggle"
        );
    }

    @Override
    public FrameBufferExampleDrawable setup() {
        return new FrameBufferExampleDrawable(sharedAssets.getGlow());
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) drawable.toggleDrawDirect();
    }

    @Override
    public void resize(int width, int height) {
        if (height == 0) return;
        if (drawable.getFrameBuffer() != null) drawable.getFrameBuffer().dispose();

        drawable.setFrameBuffer(new FrameBuffer(RGBA8888, width, height, false));
    }
}