package com.epicness.alejandria.showcase.modules.animations;

import com.epicness.alejandria.showcase.logic.Module;

public class SpriteRotationAnimation extends Module<SpriteRotationAnimationDrawable> {

    public SpriteRotationAnimation() {
        super(
                "Sprite Rotation Animation",
                "Demonstration of rotation around center and rotation around a custom origin"
        );
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