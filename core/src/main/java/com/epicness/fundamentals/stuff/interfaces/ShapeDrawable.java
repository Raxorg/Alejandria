package com.epicness.fundamentals.stuff.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Drawable2D;

public interface ShapeDrawable extends Drawable2D {

    @Override
    default void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        draw(shapeDrawer);
    }

    void draw(ShapeDrawerPlus shapeDrawer);
}