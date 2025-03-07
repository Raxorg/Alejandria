package com.epicness.fundamentals.stuff.interfaces;

import com.epicness.fundamentals.renderer.ShapeDrawerPlus;

public interface HasShapeDrawable extends ShapeDrawable {

    ShapeDrawable getShapeDrawable();

    @Override
    default void draw(ShapeDrawerPlus shapeDrawer) {
        getShapeDrawable().draw(shapeDrawer);
    }
}