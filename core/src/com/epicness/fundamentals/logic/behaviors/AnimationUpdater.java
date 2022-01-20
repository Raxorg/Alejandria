package com.epicness.fundamentals.logic.behaviors;

import com.epicness.fundamentals.stuff.SpritedAnimation;

public class AnimationUpdater {

    private SpritedAnimation animation;
    private boolean enabled;

    public void setup(SpritedAnimation animation) {
        this.animation = animation;
        enabled = true;
    }

    public void update(float delta) {
        if (!enabled) {
            return;
        }
        animation.addTime(delta);
    }
}