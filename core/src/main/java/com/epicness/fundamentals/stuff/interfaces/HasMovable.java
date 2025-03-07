package com.epicness.fundamentals.stuff.interfaces;

public interface HasMovable extends Movable {

    Movable getMovable();

    @Override
    default float getX() {
        return getMovable().getX();
    }

    @Override
    default void translateX(float amount) {
        getMovable().translateX(amount);
    }

    @Override
    default float getY() {
        return getMovable().getY();
    }

    @Override
    default void translateY(float amount) {
        getMovable().translateY(amount);
    }
}