package com.epicness.alejandria.showcase.logic.modules.animations;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.animations.SpriteAnimationDrawable;

public class SpriteAnimation extends Module {

    private SpriteAnimationDrawable drawable;

    public SpriteAnimation() {
        super("Sprite Animation");
    }

    @Override
    public Drawable setup() {
        return drawable = new SpriteAnimationDrawable(assets.getStickmanRunFrames());
    }

    @Override
    public void update(float delta) {
        drawable.getAnimation().addTime(delta);
    }

    @Override
    public void exit() {
        drawable = null;
    }
}