package com.epicness.fundamentals.stuff.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface Buttonable {

    boolean contains(float x, float y);

    default boolean contains(Vector2 point) {
        return contains(point.x, point.y);
    }
}