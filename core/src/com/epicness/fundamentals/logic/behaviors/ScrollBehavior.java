package com.epicness.fundamentals.logic.behaviors;

import com.badlogic.gdx.math.MathUtils;
import com.epicness.fundamentals.stuff.Scrollable;

public class ScrollBehavior {

    // Logic
    private Scrollable scrollable;
    private float pivot, speed, progress;
    private float minY, maxY;
    private boolean enabled;

    public void setup(Scrollable scrollable, float minY, float maxY) {
        this.scrollable = scrollable;
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
        if (scrollable == null || !enabled) {
            return;
        }
        scrollable.translateY(-speed);

        if (scrollable.getY() > maxY) {
            scrollable.setY(maxY);
        }
        if (scrollable.getY() < minY) {
            scrollable.setY(minY);
        }

        speed = MathUtils.lerp(speed, 0f, progress);
        progress += delta / 3f;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}