package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.interfaces.ColRowSupplier;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Drawable2D;

public abstract class VisibleGrid<T extends Drawable2D & Movable> extends GenericGrid<T> implements Movable, Drawable2D {

    public final float cellSize, cellSpacing;

    public VisibleGrid(int cols, int rows, float cellSize, float cellSpacing, ColRowSupplier<T> supplier) {
        super(cols, rows, supplier);
        this.cellSize = cellSize;
        this.cellSpacing = cellSpacing;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        traverseCells(cell -> cell.draw(spriteBatch, shapeDrawer));
    }

    @Override
    public float getX() {
        return getCell(0, 0).getX();
    }

    @Override
    public void translateX(float amount) {
        traverseCells(cell -> cell.translateX(amount));
    }

    @Override
    public float getY() {
        return getCell(0, 0).getY();
    }

    @Override
    public void translateY(float amount) {
        traverseCells(cell -> cell.translateY(amount));
    }

    public void center(float x, float y) {
        float gridWidth = cols * cellSize + cellSpacing * (cols - 1);
        float gridHeight = rows * cellSize + cellSpacing * (rows - 1);
        float gridX = x - gridWidth * 0.5f;
        float gridY = y - gridHeight * 0.5f;
        traverseCells((col, row, cell) -> {
            float cellX = gridX + cellSize * col + cellSpacing * col;
            float cellY = gridY + cellSize * row + cellSpacing * row;
            cell.setPosition(cellX, cellY);
        });
    }

    public T getCell(float x, float y) {
        x -= getX();
        y -= getY();

        float extendedCellSize = cellSize + cellSpacing;

        int col = (int) (x / extendedCellSize);
        int row = (int) (y / extendedCellSize);

        float xInCell = x % extendedCellSize;
        float yInCell = y % extendedCellSize;

        if (xInCell < 0f || yInCell < 0f) return null;

        if (xInCell <= cellSize && yInCell <= cellSize) return getCell(col, row);

        return null;
    }
}