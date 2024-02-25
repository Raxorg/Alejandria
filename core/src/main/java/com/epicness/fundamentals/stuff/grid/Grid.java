package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.Color;

public abstract class Grid {

    protected final int columns, rows;

    public Grid(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public abstract void setColor(Color color);

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}