package com.epicness.alejandria.showcase.logic.modules.animations;

import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.animations.SpriteTransformAnimationDrawable;

public class SpriteTransformAnimation extends Module {

    private SpriteTransformAnimationDrawable drawable;
    private float time;

    public SpriteTransformAnimation() {
        super("Sprite Transform Animation");
    }

    @Override
    public void setup() {
        drawable = new SpriteTransformAnimationDrawable(sharedAssets.getPixel(), sharedAssets.getWeirdShape());
        stuff.getShowcase().setDrawable(drawable);
    }

    @Override
    public void update(float delta) {
        time += delta / 5f;
        if (time >= 1f) {
            time = 0f;
        }
        float rotation = MathUtils.map(0f, 1f, 0f, 360f, time);
        drawable.getSprited1().setRotation(rotation);
        drawable.getSprited2().setRotation(rotation);
    }
}