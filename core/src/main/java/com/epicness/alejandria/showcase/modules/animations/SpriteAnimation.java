package com.epicness.alejandria.showcase.modules.animations;

import com.epicness.alejandria.showcase.logic.Module;

public class SpriteAnimation extends Module<SpriteAnimationDrawable> {

    public SpriteAnimation() {
        super(
            "Sprite Animation",
            "The Texture in the left contains all the frames of the animation\n\n" +
                "Frame rate is set to 20 frames per second"
        );
    }

    @Override
    public SpriteAnimationDrawable setup() {
        return new SpriteAnimationDrawable(assets.getStickmanRun(), assets.getStickmanRunFrames());
    }

    @Override
    public void update(float delta) {
        drawable.getAnimation().addTime(delta);
    }
}