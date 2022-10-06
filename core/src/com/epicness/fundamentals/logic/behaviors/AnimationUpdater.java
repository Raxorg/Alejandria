package com.epicness.fundamentals.logic.behaviors;

import com.epicness.fundamentals.stuff.SpritedAnimation;

public class AnimationUpdater {

    private SpritedAnimation animation;
    private boolean enabled;

    public void setAnimation(SpritedAnimation animation) {
        this.animation = animation;
        enabled = true;
    }

    public void update(float delta) {
        if (enabled) {
            animation.addTime(delta);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}