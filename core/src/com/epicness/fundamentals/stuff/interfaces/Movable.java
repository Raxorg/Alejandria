package com.epicness.fundamentals.stuff.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface Movable {

    void translateX(float amount);

    void translateY(float amount);

    default void translate(float xAmount, float yAmount) {
        translateX(xAmount);
        translateY(yAmount);
    }

    default void translate(Vector2 amount) {
        translateX(amount.x);
        translateY(amount.y);
    }

    float getX();

    default void setX(float x) {
        translateX(x - getX());
    }

    default void copyX(Movable other) {
        setX(other.getX());
    }

    float getY();

    default void setY(float y) {
        translateY(y - getY());
    }

    default void copyY(Movable other) {
        setY(other.getY());
    }

    default Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }

    default void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    default void setPosition(Vector2 position) {
        setPosition(position.x, position.y);
    }

    default void copyPosition(Movable other) {
        setPosition(other.getPosition());
    }
}