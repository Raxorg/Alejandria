package com.epicness.alejandria.showcase.modules.masking;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.epicness.alejandria.showcase.logic.Module;


public class AlphaMasking extends Module<AlphaMaskingDrawable> {

    public AlphaMasking() {
        super(
            "Alpha Masking",
            "We mask the sprite on the left with the sprite on the right\n\n" +
                "The mask determines the transparency of the result"
        );
    }

    @Override
    public AlphaMaskingDrawable setup() {
        sharedAssets.getWeirdShape().getTexture().setFilter(Linear, Linear);
        return new AlphaMaskingDrawable(sharedAssets.getWeirdShape(), assets.getGlow(), assets.getAdd(), assets.getEqual());
    }

    @Override
    public void resize(int width, int height) {
        if (height == 0) return;
        if (drawable.getFrameBuffer() != null) drawable.getFrameBuffer().dispose();

        drawable.setFrameBuffer(new FrameBuffer(RGBA8888, width, height, false));
    }
}