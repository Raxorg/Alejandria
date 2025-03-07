package com.epicness.fundamentals.logic.behaviors.components;

import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.logic.ResultListener;
import com.epicness.fundamentals.stuff.interfaces.Movable;

public interface HasProgressMovement {

    ProgressMovement getProgressMovement();

    default void updateProgressMovement(float delta) {
        getProgressMovement().update(delta);
    }

    default void setProgressMovementStart(Vector2 start) {
        getProgressMovement().setStart(start);
    }

    default void setProgressMovementDestination(float x, float y) {
        getProgressMovement().setDestination(x, y);
    }

    default void setProgressMovementDestination(Vector2 end) {
        getProgressMovement().setDestination(end);
    }

    default void resetProgressMovement() {
        getProgressMovement().resetProgress();
    }

    default void setProgressMovementSpeed(float speed) {
        getProgressMovement().setSpeed(speed);
    }

    default void setProgressMovementCompletionListener(ResultListener<Movable> completionListener) {
        getProgressMovement().setCompletionListener(completionListener);
    }
}