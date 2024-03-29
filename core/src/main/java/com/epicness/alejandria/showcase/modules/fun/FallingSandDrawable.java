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
import com.epicness.fundamentals.stuff.shapes.bidimensional.Rectangle;

public class FallingSandDrawable implements ModuleDrawable {

    private final ShapeDrawerPlus shapeDrawer;
    private final Rectangle[][] sandMatrix;
    private final Rectangle frame;

    public FallingSandDrawable(ShapeDrawerPlus shapeDrawer) {
        this.shapeDrawer = shapeDrawer;

        sandMatrix = new Rectangle[SAND_DIMENSION][];
        float x, y;
        for (int column = 0; column < SAND_DIMENSION; column++) {
            sandMatrix[column] = new Rectangle[SAND_DIMENSION];
            for (int row = 0; row < SAND_DIMENSION; row++) {
                x = SHOWCASE_X + column * SAND_SIZE;
                y = SHOWCASE_Y + row * SAND_SIZE;
                sandMatrix[column][row] = new Rectangle();
                sandMatrix[column][row].set(x, y, SAND_SIZE, SAND_SIZE);
                sandMatrix[column][row].borderColor.set(CLEAR);
            }
        }

        frame = new Rectangle(BLACK.cpy());
        frame.set(SHOWCASE_X, SHOWCASE_Y, SHOWCASE_SIZE, SHOWCASE_SIZE);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
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
    public void drawDebug(ShapeRendererPlus shapeRenderer) {

    }

    public Rectangle[][] getSandMatrix() {
        return sandMatrix;
    }
}