package com.epicness.alejandria.showcase.modules.patterns;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.alejandria.showcase.constants.PatternsConstants.PHYLLOTAXIS_CIRCLES;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;

public class PhyllotaxisDrawable implements ModuleDrawable {

    private final Circle[] circles;

    public PhyllotaxisDrawable() {
        circles = new Circle[PHYLLOTAXIS_CIRCLES];
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        shapeRenderer.begin(Filled);
        for (int i = 0; i < PHYLLOTAXIS_CIRCLES; i++) {
            circles[i].draw(shapeRenderer);
        }
        shapeRenderer.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public Circle[] getCircles() {
        return circles;
    }
}