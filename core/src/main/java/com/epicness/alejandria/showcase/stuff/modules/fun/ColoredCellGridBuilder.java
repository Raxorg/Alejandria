package com.epicness.alejandria.showcase.stuff.modules.fun;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.stuff.grid.CellGridBuilder;

public class ColoredCellGridBuilder extends CellGridBuilder<ColoredCell> {

    public ColoredCellGridBuilder(Sprite sprite) {
        super(new ColoredCellBuilder(sprite));
    }
}