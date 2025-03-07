package com.epicness.fundamentals.stuff.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface HasCenter {

    float getCenterX();

    float getCenterY();

    default Vector2 getCenter(Vector2 result) {
        return result.set(getCenterX(), getCenterY());
    }

    default Vector2 getCenter() {
        return getCenter(new Vector2());
    }
}