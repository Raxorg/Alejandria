package com.epicness.alejandria.showcase.modules.animations;

import com.epicness.alejandria.showcase.logic.Module;

public class SpriteRotationAnimation extends Module<SpriteRotationAnimationModuleDrawable> {

    public SpriteRotationAnimation() {
        super(
                "Sprite Rotation Animation",
                "Demonstration of rotation around center and rotation around a custom origin"
        );
    }

    @Override
    public SpriteRotationAnimationModuleDrawable setup() {
        return new SpriteRotationAnimationModuleDrawable(
                sharedAssets.getPixel(), sharedAssets.getWeirdShape()
        );
    }

    @Override
    public void update(float delta) {
        drawable.getPixel().rotate(90f * delta);
        drawable.getWeirdShape().rotate(-90f * delta);
    }
}