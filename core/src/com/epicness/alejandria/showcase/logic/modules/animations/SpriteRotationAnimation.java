package com.epicness.alejandria.showcase.logic.modules.animations;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.animations.SpriteRotationAnimationDrawable;

public class SpriteRotationAnimation extends Module<SpriteRotationAnimationDrawable> {

    public SpriteRotationAnimation() {
        super("Sprite Rotation Animation", "Rotation around center and rotation around a custom origin");
    }

    @Override
    public SpriteRotationAnimationDrawable setup() {
        return new SpriteRotationAnimationDrawable(
                sharedAssets.getPixel(), sharedAssets.getWeirdShape()
        );
    }

    @Override
    public void update(float delta) {
        drawable.getPixel().rotate(90f * delta);
        drawable.getWeirdShape().rotate(-90f * delta);
    }
}