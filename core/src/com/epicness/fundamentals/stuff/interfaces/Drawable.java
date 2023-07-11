package com.epicness.fundamentals.stuff.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeBatch;

public interface Drawable {

    void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch);

    default void draw(SpriteBatch spriteBatch) {
        draw(spriteBatch, null);
    }

    default void draw(ShapeBatch shapeBatch) {
        draw(null, shapeBatch);
    }

    void drawDebug(ShapeBatch shapeBatch);
}