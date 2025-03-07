package com.epicness.alejandria.showcase.modules.fun;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.alejandria.showcase.constants.FunConstants.CELL_SIZE;
import static com.epicness.alejandria.showcase.constants.FunConstants.CIRCLE_RADIUS;
import static com.epicness.alejandria.showcase.constants.FunConstants.GRID_COLUMNS;
import static com.epicness.alejandria.showcase.constants.FunConstants.GRID_ROWS;
import static com.epicness.alejandria.showcase.constants.FunConstants.GRID_X;
import static com.epicness.alejandria.showcase.constants.FunConstants.GRID_Y;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.SnapshotArray;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.alejandria.showcase.stuff.modules.fun.ColorBall;
import com.epicness.alejandria.showcase.stuff.modules.fun.ColoredCell;
import com.epicness.alejandria.showcase.stuff.modules.fun.ColoredCellGrid;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.stuff.grid.CellGrid;
import com.epicness.fundamentals.stuff.shapes.bidimensional.CirclePlus;

public class ReactiveGridDrawable implements ModuleDrawable {

    private final ColoredCellGrid grid;
    private final SnapshotArray<SpritePlus> colorBombs;
    private final SnapshotArray<ColorBall> colorBalls;
    private final CirclePlus circle;

    public ReactiveGridDrawable(Sprite cellSprite) {
        grid = new ColoredCellGrid(cellSprite, GRID_COLUMNS, GRID_ROWS);
        grid.setCellSize(CELL_SIZE);
        grid.translate(GRID_X, GRID_Y);

        colorBombs = new SnapshotArray<>();
        colorBalls = new SnapshotArray<>();

        circle = new CirclePlus(CIRCLE_RADIUS);
        circle.setThickness(3f);
        circle.setColor(RED);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        ScreenUtils.clear(BLACK);
        spriteBatch.begin();
        grid.draw(spriteBatch);
        for (int i = 0; i < colorBombs.size; i++) {
            colorBombs.get(i).draw(spriteBatch);
        }
        for (int i = 0; i < colorBalls.size; i++) {
            colorBalls.get(i).draw(spriteBatch);
        }
        circle.draw(shapeDrawer);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }

    public CellGrid<ColoredCell> getGrid() {
        return grid;
    }

    public SnapshotArray<SpritePlus> getColorBombs() {
        return colorBombs;
    }

    public SnapshotArray<ColorBall> getColorBalls() {
        return colorBalls;
    }

    public CirclePlus getCircle() {
        return circle;
    }
}