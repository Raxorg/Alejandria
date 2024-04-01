package com.epicness.alejandria.showcase.modules.lights;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.utils.Random;

import java.util.List;

public class SimpleLights extends Module<SimpleLightsDrawable> {

    private List<Sprite> lights;

    public SimpleLights() {
        super("Simple Lights", "Click to spawn lights\n\nThese don't cast shadows and their colors are random");
    }

    @Override
    protected SimpleLightsDrawable setup() {
        drawable = new SimpleLightsDrawable();
        lights = drawable.getLights();
        return drawable;
    }

    @Override
    public void touchDown(float x, float y) {
        Sprite light = new Sprite(sharedAssets.getGlow());
        light.setOriginCenter();
        light.setOriginBasedPosition(x, y - SHOWCASE_STRIPE_HEIGHT);
        light.setColor(Random.opaqueColor());
        lights.add(light);
    }
}