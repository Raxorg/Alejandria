package com.epicness.fundamentals.stuff.grid;

public class DefaultCellGridBuilder extends CellGridBuilder<Cell> {

    public DefaultCellGridBuilder(DefaultCellBuilder cellBuilder) {
        super(cellBuilder);
    }

    public DefaultCellGridBuilder columns(int columns) {
        this.columns = columns;
        return this;
    }

    public DefaultCellGridBuilder rows(int rows) {
        this.rows = rows;
        return this;
    }
}