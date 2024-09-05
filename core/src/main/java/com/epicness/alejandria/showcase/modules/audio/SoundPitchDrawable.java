package com.epicness.alejandria.showcase.modules.audio;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.alejandria.showcase.constants.AudioConstants.SOUNDBOARD_CELL_SIZE;
import static com.epicness.alejandria.showcase.constants.AudioConstants.SOUNDBOARD_CIRCLE_RADIUS;
import static com.epicness.alejandria.showcase.constants.AudioConstants.SOUNDBOARD_COLS;
import static com.epicness.alejandria.showcase.constants.AudioConstants.SOUNDBOARD_ROWS;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.fundamentals.constants.ColorConstants.LIGHT_DIRT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.grid.Cell;
import com.epicness.fundamentals.stuff.grid.DefaultCellGrid;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;

public class SoundPitchDrawable implements ModuleDrawable {

    private final DefaultCellGrid grid;
    private final Circle[] circles;

    public SoundPitchDrawable(Sprite cell) {
        grid = new DefaultCellGrid(cell, SOUNDBOARD_COLS, SOUNDBOARD_ROWS);
        grid.setCellSize(SOUNDBOARD_CELL_SIZE);
        grid.translate(VIEWPORT_HALF_WIDTH - grid.getWidth() / 2f, SHOWCASE_STRIPE_HEIGHT * 2f);
        grid.setColor(LIGHT_DIRT);

        circles = new Circle[SOUNDBOARD_ROWS];
        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle(
                grid.cells[0][i].getX(), grid.cells[0][i].getCenterY() - SOUNDBOARD_CIRCLE_RADIUS,
                SOUNDBOARD_CIRCLE_RADIUS, RED
            );
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        grid.draw(spriteBatch);
        for (int i = 0; i < circles.length; i++) {
            circles[i].draw(shapeDrawer);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {

    }

    public Cell[][] getCells() {
        return grid.cells;
    }

    public Circle[] getCircles() {
        return circles;
    }
}