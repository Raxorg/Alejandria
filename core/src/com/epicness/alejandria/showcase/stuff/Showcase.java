package com.epicness.alejandria.showcase.stuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.interfaces.Drawable;

public class Showcase {

    private Drawable moduleDrawable;

    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        moduleDrawable.draw(spriteBatch, shapeBatch);
    }

    public void setModuleDrawable(Drawable moduleDrawable) {
        this.moduleDrawable = moduleDrawable;
    }
}