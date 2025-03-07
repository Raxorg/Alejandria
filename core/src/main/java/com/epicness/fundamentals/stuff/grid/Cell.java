package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.stuff.SpritePlus;

public class Cell extends SpritePlus {

    private final int column, row;

    public Cell(Sprite sprite, int column, int row) {
        super(sprite);
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}