package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.utils.Array;
import com.epicness.fundamentals.stuff.interfaces.ColRowSupplier;

public class GenericGrid<T> {

    public final int cols, rows;
    protected final Array<Array<T>> cells;

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

    public T getCell(int col, int row) {
        return cells.get(col).get(row);
    }
}