package com.epicness.alejandria.showcase.logic.modules.animations;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.animations.SpriteRotationAnimationDrawable;

public class SpriteRotationAnimation extends Module {

    private SpriteRotationAnimationDrawable drawable;

    public SpriteRotationAnimation() {
        super("Sprite Rotation Animation");
    }

    @Override
    public Drawable setup() {
        return drawable = new SpriteRotationAnimationDrawable(
                sharedAssets.getPixel(), sharedAssets.getWeirdShape()
        );
    }

    @Override
    public void update(float delta) {
        drawable.getSprited1().rotate(90f * delta);
        drawable.getSprited2().rotate(-90f * delta);
    }
}