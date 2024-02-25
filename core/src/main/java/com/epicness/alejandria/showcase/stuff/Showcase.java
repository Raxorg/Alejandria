package com.epicness.alejandria.showcase.stuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class Showcase {

    private ModuleDrawable moduleDrawable;

    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        moduleDrawable.draw(spriteBatch, shapeRenderer);
    }

    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        moduleDrawable.drawDebug(shapeRenderer);
    }

    public void setModuleDrawable(ModuleDrawable moduleDrawable) {
        this.moduleDrawable = moduleDrawable;
    }
}