package com.epicness.alejandria.showcase.logic.modules.animations;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.animations.SpriteAnimationDrawable;

public class SpriteAnimation extends Module<SpriteAnimationDrawable> {

    public SpriteAnimation() {
        super(
                "Sprite Animation",
                "The Texture in the left contains all the frames of the animation"
        );
    }

    @Override
    public SpriteAnimationDrawable setup() {
        return new SpriteAnimationDrawable(assets.getFrames(), assets.getStickmanRunFrames());
    }

    @Override
    public void update(float delta) {
        drawable.getAnimation().addTime(delta);
    }
}