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
import com.epicness.alejandria.showcase.stuff.modules.fun.ColoredCellBuilder;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.grid.CellGrid;
import com.epicness.fundamentals.stuff.grid.CellGridBuilder;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;

public class ReactiveGridDrawable implements ModuleDrawable {

    private final ShapeDrawerPlus shapeDrawer;
    private final CellGrid<ColoredCell> grid;
    private final SnapshotArray<Sprited> colorBombs;
    private final SnapshotArray<ColorBall> colorBalls;
    private final Circle circle;

    public ReactiveGridDrawable(ShapeDrawerPlus shapeDrawer, Sprite cellSprite) {
        this.shapeDrawer = shapeDrawer;

        CellGridBuilder<ColoredCell> builder = new CellGridBuilder<>(
            new ColoredCellBuilder().sprite(cellSprite))
            .columns(GRID_COLUMNS)
            .rows(GRID_ROWS);
        grid = new CellGrid<>(builder);
        grid.setCellSize(CELL_SIZE);
        grid.translate(GRID_X, GRID_Y);

        colorBombs = new SnapshotArray<>();
        colorBalls = new SnapshotArray<>();

        circle = new Circle(CIRCLE_RADIUS);
        circle.setThickness(3f);
        circle.setColor(RED);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
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
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public CellGrid<ColoredCell> getGrid() {
        return grid;
    }

    public SnapshotArray<Sprited> getColorBombs() {
        return colorBombs;
    }

    public SnapshotArray<ColorBall> getColorBalls() {
        return colorBalls;
    }

    public Circle getCircle() {
        return circle;
    }
}