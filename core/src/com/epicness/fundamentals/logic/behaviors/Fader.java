package com.epicness.fundamentals.logic.behaviors;

import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.logic.CompletionListener;
import com.epicness.fundamentals.stuff.SharedStuff;

public class Fader {

    // Structure
    private SharedStuff stuff;
    // Logic
    private float time, fadeTime;
    private boolean fadingIn, fadingOut, enabled;
    private CompletionListener completionListener;

    public void setup(float fadeTime) {
        this.fadeTime = fadeTime;
        enabled = true;
    }

    public void update(float delta) {
        if (!enabled) {
            return;
        }
        Color color;
        float progress = 0f;
        if (fadingIn) {
            progress = Math.min(time / fadeTime, 1f);
            color = Color.CLEAR.cpy().lerp(Color.BLACK, progress);
            stuff.getFader().setColor(color);
        } else if (fadingOut) {
            progress = Math.min(time / fadeTime, 1f);
            color = Color.BLACK.cpy().lerp(Color.CLEAR, progress);
            stuff.getFader().setColor(color);
        }
        if (completionListener != null && progress == 1f) {
            fadingIn = false;
            fadingOut = false;
            completionListener.onComplete();
            completionListener = null;
        }
        time += delta;
    }

    public void fadeIn() {
        time = 0f;
        fadingIn = true;
        fadingOut = false;
    }

    public void fadeIn(CompletionListener completionListener) {
        fadeIn();
        this.completionListener = completionListener;
    }

    public void fadeOut() {
        time = 0f;
        fadingIn = false;
        fadingOut = true;
    }

    public void fadeOut(CompletionListener completionListener) {
        fadeOut();
        this.completionListener = completionListener;
    }

    // Structure
    public void setStuff(SharedStuff stuff) {
        this.stuff = stuff;
    }
}