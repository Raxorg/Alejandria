package com.epicness.alejandria.showcase.stuff.modules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public interface ModuleDrawable {

    void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer);

    void drawDebug(ShapeRendererPlus shapeRenderer);
}