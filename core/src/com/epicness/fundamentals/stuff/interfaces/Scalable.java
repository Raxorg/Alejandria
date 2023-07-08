package com.epicness.fundamentals.stuff.interfaces;

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

    default void setSize(float width, float height) {
        setWidth(width);
        setHeight(height);
    }

    default void setSize(float size) {
        setSize(size, size);
    }
}