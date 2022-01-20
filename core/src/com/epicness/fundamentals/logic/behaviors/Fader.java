package com.epicness.fundamentals.logic.behaviors;

import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.stuff.Sprited;

public class Fader {

    private Sprited fadeSprite;
    private float time, fadeTime;
    private boolean fadingIn, fadingOut, enabled;

    public void setup(Sprited fadeSprite, float fadeTime) {
        this.fadeSprite = fadeSprite;
        this.fadeTime = fadeTime;
        enabled = true;
    }

    public void update(float delta) {
        if (!enabled) {
            return;
        }
        Color color;
        if (fadingIn) {
            float progress = Math.min(time / fadeTime, 1f);
            color = Color.CLEAR.cpy().lerp(Color.BLACK, progress);
            fadeSprite.setColor(color);
        } else if (fadingOut) {
            float progress = Math.min(time / fadeTime, 1f);
            color = Color.BLACK.cpy().lerp(Color.CLEAR, progress);
            fadeSprite.setColor(color);
        }
        time += delta;
    }

    public void fadeIn() {
        time = 0f;
        fadingIn = true;
        fadingOut = false;
    }

    public void fadeOut() {
        time = 0f;
        fadingIn = false;
        fadingOut = true;
    }
}