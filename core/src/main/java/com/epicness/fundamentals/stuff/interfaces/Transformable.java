package com.epicness.fundamentals.stuff.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface Transformable extends Movable, Scalable, Rotatable {

    default float getEndX() {
        return getX() + getWidth();
    }

    default float getEndY() {
        return getY() + getHeight();
    }

    default float getCenterX() {
        return getX() + getWidth() / 2f;
    }

    default float getCenterY() {
        return getY() + getHeight() / 2f;
    }

    default Vector2 getCenter(Vector2 result) {
        return result.set(getCenterX(), getCenterY());
    }

    default Vector2 getCenter() {
        return getCenter(new Vector2());
    }
}