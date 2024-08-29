package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class DefaultCellGridBuilder extends CellGridBuilder<Cell> {

    public DefaultCellGridBuilder(Sprite sprite) {
        super(new DefaultCellBuilder(sprite));
    }
}