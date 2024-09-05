package com.epicness.alejandria.showcase.modules.lights;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.utils.Random;

import java.util.List;

public class SimpleLights extends Module<SimpleLightsDrawable> {

    private TextureRegion bufferRegion;
    private List<Sprite> lights;

    public SimpleLights() {
        super("Simple Lights", "Click to spawn lights\n\nThese don't cast shadows and their colors are random");
    }

    @Override
    protected SimpleLightsDrawable setup() {
        drawable = new SimpleLightsDrawable();
        bufferRegion = drawable.getBufferRegion();
        lights = drawable.getLights();
        return drawable;
    }

    @Override
    public void resize(int width, int height) {
        if (height == 0) return;
        if (drawable.getLightBuffer() != null) drawable.getLightBuffer().dispose();

        drawable.setLightBuffer(new FrameBuffer(RGBA8888, width, height, false));
        bufferRegion.setTexture(drawable.getLightBuffer().getColorBufferTexture());
    }

    @Override
    public void touchDown(float x, float y, int button) {
        Sprite light = new Sprite(sharedAssets.getGlow());
        light.setOriginCenter();
        light.setOriginBasedPosition(x, y - SHOWCASE_STRIPE_HEIGHT);
        light.setColor(Random.opaqueColor());
        lights.add(light);
    }
}