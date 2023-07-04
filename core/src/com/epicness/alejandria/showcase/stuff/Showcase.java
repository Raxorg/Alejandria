package com.epicness.alejandria.showcase.stuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Showcase {

    private Drawable moduleDrawable;

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        moduleDrawable.draw(spriteBatch, shapeRenderer);
    }

    public void setModuleDrawable(Drawable moduleDrawable) {
        this.moduleDrawable = moduleDrawable;
    }
}