package com.epicness.fundamentals.stuff.interfaces;

public interface Rotatable {

    float getRotation();

    void rotate(float degrees);

    default void setRotation(float degrees) {
        rotate(degrees - getRotation());
    }
}