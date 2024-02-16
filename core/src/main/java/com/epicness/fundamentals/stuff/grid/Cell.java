package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.stuff.Sprited;

public class Cell extends Sprited {

    private final int column, row;

    public Cell(Sprite cellSprite, int column, int row) {
        super(cellSprite);
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