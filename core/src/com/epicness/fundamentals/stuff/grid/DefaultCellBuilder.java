package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class DefaultCellBuilder extends CellBuilder<Cell> {

    @Override
    public DefaultCellBuilder sprite(Sprite sprite) {
        this.sprite = sprite;
        return this;
    }

    @Override
    public Cell[][] buildColumns(int columns) {
        return new Cell[columns][];
    }

    @Override
    public Cell[] buildRow(int rows) {
        return new Cell[rows];
    }

    @Override
    public Cell buildCell() {
        return new Cell(sprite, column, row);
    }
}