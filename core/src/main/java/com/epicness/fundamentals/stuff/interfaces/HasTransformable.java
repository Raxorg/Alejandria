package com.epicness.fundamentals.stuff.interfaces;

public interface HasTransformable extends Transformable, HasMovable, HasScalable, HasRotatable {

    Transformable getTransformable();

    @Override
    default Movable getMovable() {
        return getTransformable();
    }

    @Override
    default Scalable getScalable() {
        return getTransformable();
    }

    @Override
    default Rotatable getRotatable() {
        return getTransformable();
    }
}