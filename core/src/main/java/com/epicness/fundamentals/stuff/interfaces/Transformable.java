package com.epicness.fundamentals.stuff.interfaces;

public interface Transformable extends Movable, Scalable, Rotatable, HasCenter {

    default float getEndX() {
        return getX() + getWidth();
    }

    default float getEndY() {
        return getY() + getHeight();
    }

    @Override
    default float getCenterX() {
        return getX() + getWidth() * 0.5f;
    }

    @Override
    default float getCenterY() {
        return getY() + getHeight() * 0.5f;
    }
}