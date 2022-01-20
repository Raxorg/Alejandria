package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.stuff.Sprited;

import java.util.HashMap;

public class Cell extends Sprited {

    private final int column, row;
    private final HashMap<String, Object> properties;

    public Cell(Sprite cellSprite, int column, int row) {
        super(cellSprite);
        this.column = column;
        this.row = row;
        properties = new HashMap<>();
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public HashMap<String, Object> getProperties() {
        return properties;
    }
}