package com.epicness.fundamentals.stuff.grid;

public class CellGridBuilder<T extends Cell> {

    private final CellBuilder<T> cellBuilder;
    protected int columns, rows;

    public CellGridBuilder(CellBuilder<T> cellBuilder) {
        this.cellBuilder = cellBuilder;
        columns = rows = 1;
    }

    public CellGridBuilder<T> columns(int columns) {
        this.columns = columns;
        return this;
    }

    public CellGridBuilder<T> rows(int rows) {
        this.rows = rows;
        return this;
    }

    public T[][] build() {
        T[][] cells = cellBuilder.buildColumns(columns);
        for (int column = 0; column < columns; column++) {
            cells[column] = cellBuilder.buildRow(rows);
            for (int row = 0; row < rows; row++) {
                cells[column][row] = cellBuilder.column(column).row(row).buildCell();
            }
        }
        return cells;
    }

    public CellBuilder<T> getCellBuilder() {
        return cellBuilder;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}