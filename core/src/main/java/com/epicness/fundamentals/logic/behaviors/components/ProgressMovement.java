package com.epicness.fundamentals.logic.behaviors.components;

import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.logic.ResultListener;
import com.epicness.fundamentals.stuff.interfaces.Movable;

public class ProgressMovement {

    private final Movable movable;
    private final Vector2 start, current, destination;
    private float progress, speed;
    private ResultListener<Movable> completionListener;

    public ProgressMovement(Movable movable) {
        this.movable = movable;
        start = new Vector2();
        current = new Vector2();
        destination = new Vector2();
        progress = 0f;
        speed = 1f;
    }

    void update(float delta) {
        if (progress == 1f) return;

        progress = Math.min(progress + speed * delta, 1f);
        current.set(start).lerp(destination, progress);
        movable.setPosition(current);

        if (progress == 1f && completionListener != null) completionListener.onResult(movable);
    }

    public void setStart(Vector2 start) {
        this.start.set(start);
    }

    public void setDestination(float x, float y) {
        destination.set(x, y);
    }

    public void setDestination(Vector2 end) {
        destination.set(end);
    }

    void resetProgress() {
        progress = 0f;
    }

    void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setCompletionListener(ResultListener<Movable> completionListener) {
        this.completionListener = completionListener;
    }
}