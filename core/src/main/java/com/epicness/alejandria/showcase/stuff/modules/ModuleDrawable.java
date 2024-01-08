package com.epicness.alejandria.showcase.stuff.modules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public interface ModuleDrawable {

    void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer);

    default void draw(SpriteBatch spriteBatch) {
        draw(spriteBatch, null);
    }

    default void draw(ShapeRendererPlus shapeRenderer) {
        draw(null, shapeRenderer);
    }

    void drawDebug(ShapeRendererPlus shapeRenderer);
}