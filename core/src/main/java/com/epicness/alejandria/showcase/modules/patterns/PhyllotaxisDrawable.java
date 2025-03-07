package com.epicness.alejandria.showcase.modules.patterns;

import static com.epicness.alejandria.showcase.constants.PatternsConstants.PHYLLOTAXIS_CIRCLES;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.CirclePlus;

public class PhyllotaxisDrawable implements ModuleDrawable {

    private final CirclePlus[] circles;

    public PhyllotaxisDrawable() {
        circles = new CirclePlus[PHYLLOTAXIS_CIRCLES];
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        for (int i = 0; i < circles.length; i++) {
            circles[i].draw(shapeDrawer);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }

    public CirclePlus[] getCircles() {
        return circles;
    }
}