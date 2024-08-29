package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;

@SuppressWarnings("unchecked")
public abstract class CellBuilder<T extends Cell> {

    protected Sprite sprite;
    protected int column, row;

    public CellBuilder(Sprite sprite) {
        sprite(sprite);
    }

    public <B extends CellBuilder<T>> B sprite(Sprite sprite) {
        this.sprite = sprite;
        return (B) this;
    }

    public CellBuilder<T> column(int column) {
        this.column = column;
        return this;
    }

    public CellBuilder<T> row(int row) {
        this.row = row;
        return this;
    }

    public abstract T[][] buildColumns(int columns);

    public abstract T[] buildRow(int rows);

    public abstract T buildCell();
}