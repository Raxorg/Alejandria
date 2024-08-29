package com.epicness.alejandria.showcase.stuff.modules.fun;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.stuff.grid.CellBuilder;

public class ColoredCellBuilder extends CellBuilder<ColoredCell> {

    public ColoredCellBuilder(Sprite sprite) {
        super(sprite);
    }

    @Override
    public ColoredCell[][] buildColumns(int columns) {
        return new ColoredCell[columns][];
    }

    @Override
    public ColoredCell[] buildRow(int rows) {
        return new ColoredCell[rows];
    }

    @Override
    public ColoredCell buildCell() {
        return new ColoredCell(sprite, column, row);
    }
}