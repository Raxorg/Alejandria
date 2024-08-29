package com.epicness.alejandria.showcase.modules.patterns;

import static com.epicness.alejandria.showcase.constants.PatternsConstants.DOT_COUNT;
import static com.epicness.alejandria.showcase.constants.PatternsConstants.DOT_SIZE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class SpiralDrawable implements ModuleDrawable {

    private final Sprite[] dots;

    public SpiralDrawable(Sprite dot) {
        dots = new Sprite[DOT_COUNT];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Sprite(dot);
            dots[i].setSize(DOT_SIZE, DOT_SIZE);
            dots[i].setOriginCenter();
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        for (int i = 0; i < dots.length; i++) {
            dots[i].draw(spriteBatch);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public Sprite[] getDots() {
        return dots;
    }
}