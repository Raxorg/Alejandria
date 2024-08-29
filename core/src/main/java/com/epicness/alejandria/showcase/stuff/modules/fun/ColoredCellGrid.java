package com.epicness.alejandria.showcase.stuff.modules.fun;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.stuff.grid.CellGrid;

public class ColoredCellGrid extends CellGrid<ColoredCell> {

    public ColoredCellGrid(Sprite sprite, int columns, int rows) {
        super(new ColoredCellGridBuilder(sprite).columns(columns).rows(rows));
    }
}