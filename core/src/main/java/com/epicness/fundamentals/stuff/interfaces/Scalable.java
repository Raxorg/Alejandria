package com.epicness.fundamentals.stuff.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface Scalable {

    void stretchWidth(float amount);

    void stretchHeight(float amount);

    default void stretch(float xAmount, float yAmount) {
        stretchWidth(xAmount);
        stretchHeight(yAmount);
    }

    float getWidth();

    default void setWidth(float width) {
        stretchWidth(width - getWidth());
    }

    float getHeight();

    default void setHeight(float height) {
        stretchHeight(height - getHeight());
    }

    default Vector2 getSize(Vector2 result) {
        return result.set(getWidth(), getHeight());
    }

    default Vector2 getSize() {
        return getSize(new Vector2());
    }

    default void setSize(float width, float height) {
        setWidth(width);
        setHeight(height);
    }

    default void setSize(float size) {
        setSize(size, size);
    }

    default void setSize(Vector2 size) {
        setSize(size.x, size.y);
    }
}