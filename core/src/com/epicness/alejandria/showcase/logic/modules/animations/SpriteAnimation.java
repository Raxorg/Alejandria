package com.epicness.alejandria.showcase.logic.modules.animations;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.animations.SpriteAnimationDrawable;

public class SpriteAnimation extends Module {

    private SpriteAnimationDrawable drawable;
    private float time;

    public SpriteAnimation() {
        super("Sprite Animation");
    }

    @Override
    public void setup() {
        drawable = new SpriteAnimationDrawable(sharedAssets.getPixel(), sharedAssets.getWeirdShape());
        stuff.getShowcase().setDrawable(drawable);
    }

    @Override
    public void update(float delta) {
        drawable.getAnimation().addTime(delta);
    }
}