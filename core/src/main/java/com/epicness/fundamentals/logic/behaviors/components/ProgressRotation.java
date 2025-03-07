package com.epicness.fundamentals.logic.behaviors.components;

import com.badlogic.gdx.math.MathUtils;
import com.epicness.fundamentals.logic.ResultListener;
import com.epicness.fundamentals.stuff.interfaces.Rotatable;

public class ProgressRotation {

    private final Rotatable rotatable;
    private float startingDegrees, finalDegrees, progress, duration;
    private ResultListener<Rotatable> completionListener;

    public ProgressRotation(Rotatable rotatable) {
        this.rotatable = rotatable;
        progress = 0f;
        duration = 1f;
    }

    public void update(float delta) {
        if (progress == 1f) return;

        progress = Math.min(progress + delta / duration, 1f);
        rotatable.setRotation(MathUtils.lerp(startingDegrees, finalDegrees, progress));

        if (progress == 1f && completionListener != null) completionListener.onResult(rotatable);
    }

    public void setStartingDegrees(float startingDegrees) {
        this.startingDegrees = startingDegrees;
    }

    public void setFinalDegrees(float finalDegrees) {
        this.finalDegrees = finalDegrees;
    }

    public void resetProgress() {
        progress = 0f;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setCompletionListener(ResultListener<Rotatable> completionListener) {
        this.completionListener = completionListener;
    }
}