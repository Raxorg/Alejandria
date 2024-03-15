package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class DefaultCellGrid extends CellGrid<Cell> {

    public DefaultCellGrid(Sprite sprite, int columns, int rows) {
        super(new DefaultCellGridBuilder(sprite).columns(columns).rows(rows));
    }
}