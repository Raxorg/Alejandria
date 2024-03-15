package com.epicness.alejandria.showcase.modules.audio;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_TOP;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.LIGHT_DIRT;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.grid.Cell;
import com.epicness.fundamentals.stuff.grid.DefaultCellGrid;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Line;

public class SoundPitchDrawable implements ModuleDrawable {

    private final DefaultCellGrid grid;
    private final Line line;

    public SoundPitchDrawable(Sprite cell) {
        grid = new DefaultCellGrid(cell, 8, 5);
        grid.setCellSize(120f);
        grid.translate(CAMERA_HALF_WIDTH - grid.getWidth() / 2f, SHOWCASE_STRIPE_HEIGHT * 2f);
        grid.setColor(LIGHT_DIRT);

        line = new Line(
            grid.cells[0][0].getX(), SHOWCASE_STRIPE_HEIGHT,
            grid.cells[0][0].getX(), SHOWCASE_TOP, 5f,
            RED
        );
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        grid.draw(spriteBatch);
        line.draw(shapeDrawer);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {

    }

    public Cell[][] getCells() {
        return grid.cells;
    }

    public Line getLine() {
        return line;
    }
}