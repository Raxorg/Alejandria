package com.epicness.alejandria.showcase.modules.fun;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.CLEAR;
import static com.epicness.alejandria.showcase.constants.FunConstants.SAND_DIMENSION;
import static com.epicness.alejandria.showcase.constants.FunConstants.SAND_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_X;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_Y;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.RectanglePlus;

public class FallingSandDrawable implements ModuleDrawable {

    private final RectanglePlus[][] sandMatrix;
    private final RectanglePlus frame;

    public FallingSandDrawable() {
        sandMatrix = new RectanglePlus[SAND_DIMENSION][];
        float x, y;
        for (int column = 0; column < SAND_DIMENSION; column++) {
            sandMatrix[column] = new RectanglePlus[SAND_DIMENSION];
            for (int row = 0; row < SAND_DIMENSION; row++) {
                x = SHOWCASE_X + column * SAND_SIZE;
                y = SHOWCASE_Y + row * SAND_SIZE;
                sandMatrix[column][row] = new RectanglePlus(x, y, SAND_SIZE, SAND_SIZE, CLEAR.cpy());
            }
        }

        frame = new RectanglePlus(SHOWCASE_X, SHOWCASE_Y, SHOWCASE_SIZE, SHOWCASE_SIZE, BLACK, CLEAR);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        for (int column = 0; column < SAND_DIMENSION; column++) {
            for (int row = 0; row < SAND_DIMENSION; row++) {
                sandMatrix[column][row].drawFilled(shapeDrawer);
            }
        }
        frame.drawBorder(shapeDrawer);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {

    }

    public RectanglePlus[][] getSandMatrix() {
        return sandMatrix;
    }
}