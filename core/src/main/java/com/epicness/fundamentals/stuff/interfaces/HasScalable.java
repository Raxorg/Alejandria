package com.epicness.fundamentals.stuff.interfaces;

public interface HasScalable extends Scalable {

    Scalable getScalable();

    @Override
    default float getWidth() {
        return getScalable().getWidth();
    }

    @Override
    default void stretchWidth(float amount) {
        getScalable().stretchWidth(amount);
    }

    @Override
    default float getHeight() {
        return getScalable().getHeight();
    }

    @Override
    default void stretchHeight(float amount) {
        getScalable().stretchHeight(amount);
    }
}