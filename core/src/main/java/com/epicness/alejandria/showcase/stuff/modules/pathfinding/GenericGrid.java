package com.epicness.alejandria.showcase.stuff.modules.pathfinding;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.ColRowSupplier;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.stuff.interfaces.SpriteDrawable;

public class GenericGrid<T extends SpriteDrawable & Buttonable & Movable> {

    public final int cols, rows;
    private final Array<Array<T>> cells;

    public GenericGrid(int cols, int rows, ColRowSupplier<T> supplier) {
        this.cols = cols;
        this.rows = rows;
        cells = new Array<>(cols);
        for (int c = 0; c < cols; c++) {
            cells.add(new Array<>(rows));
            for (int r = 0; r < rows; r++) {
                cells.get(c).add(supplier.get(c, r));
            }
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                cells.get(c).get(r).draw(spriteBatch);
            }
        }
    }

    public void translateY(float amount) {
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                cells.get(c).get(r).translateY(amount);
            }
        }
    }

    public T getCell(int col, int row) {
        return cells.get(col).get(row);
    }

    public T getCellAtPosition(float x, float y) {
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if (cells.get(c).get(r).contains(x, y)) {
                    return cells.get(c).get(r);
                }
            }
        }
        return null;
    }
}