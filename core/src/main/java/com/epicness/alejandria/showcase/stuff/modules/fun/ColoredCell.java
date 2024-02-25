package com.epicness.alejandria.showcase.stuff.modules.fun;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.stuff.grid.Cell;

public class ColoredCell extends Cell {

    public final Color originalColor;
    public float colorProgress;

    public ColoredCell(Sprite cellSprite, int column, int row) {
        super(cellSprite, column, row);
        originalColor = new Color();
        colorProgress = 1f;
    }
}