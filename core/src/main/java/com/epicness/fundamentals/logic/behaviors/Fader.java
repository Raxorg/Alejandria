package com.epicness.fundamentals.logic.behaviors;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.CLEAR;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.logic.CompletionListener;
import com.epicness.fundamentals.stuff.SharedStuff;

public class Fader {

    // Structure
    private SharedStuff stuff;
    // Logic
    private float time, fadeTime;
    private boolean fadingIn, fadingOut, enabled, completionHandled;
    private CompletionListener completionListener;

    public void setup(float fadeTime) {
        this.fadeTime = fadeTime;
        enabled = true;
    }

    private void update(float delta) {
        if (!enabled) {
            return;
        }
        Color color;
        float progress = 0f;
        if (fadingIn) {
            progress = Math.min(time / fadeTime, 1f);
            color = CLEAR.cpy().lerp(BLACK, progress);
            stuff.getFader().setColor(color);
        } else if (fadingOut) {
            progress = Math.min(time / fadeTime, 1f);
            color = BLACK.cpy().lerp(CLEAR, progress);
            stuff.getFader().setColor(color);
        }
        if (completionListener != null && !completionHandled && progress == 1f) {
            fadingIn = false;
            fadingOut = false;
            completionHandled = true;
            completionListener.onComplete();
        }
        time += delta;
    }

    public void update() {
        update(Gdx.graphics.getDeltaTime());
    }

    public void fadeIn() {
        time = 0f;
        fadingIn = true;
        fadingOut = false;
    }

    public void fadeIn(CompletionListener completionListener) {
        fadeIn();
        completionHandled = false;
        this.completionListener = completionListener;
    }

    public void fadeOut() {
        time = 0f;
        fadingIn = false;
        fadingOut = true;
    }

    public void fadeOut(CompletionListener completionListener) {
        fadeOut();
        completionHandled = false;
        this.completionListener = completionListener;
    }

    // Structure
    public void setStuff(SharedStuff stuff) {
        this.stuff = stuff;
    }
}