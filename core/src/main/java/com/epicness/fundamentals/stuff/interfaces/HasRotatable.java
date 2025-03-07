package com.epicness.fundamentals.stuff.interfaces;

public interface HasRotatable extends Rotatable {

    Rotatable getRotatable();

    @Override
    default float getRotation() {
        return getRotatable().getRotation();
    }

    @Override
    default void rotate(float degrees) {
        getRotatable().rotate(degrees);
    }
}