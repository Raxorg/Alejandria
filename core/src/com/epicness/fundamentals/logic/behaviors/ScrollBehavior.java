package com.epicness.fundamentals.logic.behaviors;

import com.badlogic.gdx.math.MathUtils;
import com.epicness.fundamentals.stuff.interfaces.Movable;

public class ScrollBehavior {

    private Movable movable;
    private float pivot, speed, progress;
    private float minY, maxY;
    private boolean enabled;

    public void setup(Movable movable, float minY, float maxY) {
        this.movable = movable;
        this.minY = minY;
        this.maxY = maxY;
        enabled = true;
    }

    public void touchDown(float y) {
        speed = 0f;
        pivot = y;
        progress = 0f;
    }

    public void touchDragged(float y) {
        speed = pivot - y;
        pivot = y;
        progress = 0f;
    }

    public void update(float delta) {
        if (movable == null || !enabled) {
            return;
        }
        movable.translateY(-speed);

        if (movable.getY() > maxY) {
            movable.setY(maxY);
        }
        if (movable.getY() < minY) {
            movable.setY(minY);
        }

        speed = MathUtils.lerp(speed, 0f, progress);
        progress += delta / 3f;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}