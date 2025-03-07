package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.utils.Array;
import com.epicness.fundamentals.stuff.interfaces.ColRowSupplier;

import java.util.function.Consumer;

public class GenericGrid<T> {

    protected int cols, rows;
    private final ColRowSupplier<T> supplier;
    protected final Array<Array<T>> cells;

    public GenericGrid(int cols, int rows, ColRowSupplier<T> supplier) {
        this.cols = cols;
        this.rows = rows;
        this.supplier = supplier;
        cells = new Array<>();
        createMatrix();
    }

    public void setDimensions(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        createMatrix();
    }

    private void createMatrix() {
        cells.clear();
        for (int col = 0; col < cols; col++) {
            cells.add(new Array<>());
            for (int row = 0; row < rows; row++) {
                cells.get(col).add(supplier.get(col, row));
            }
        }
    }

    public T getCell(int col, int row) {
        return cells.get(col).get(row);
    }

    public final void traverseCells(CellConsumer<T> consumer) {
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                consumer.accept(col, row, getCell(col, row));
            }
        }
    }

    public final void traverseCells(Consumer<T> consumer) {
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                consumer.accept(getCell(col, row));
            }
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}