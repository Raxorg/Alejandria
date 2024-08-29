package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class CellGrid<T extends Cell> extends Grid {

    public final T[][] cells;

    public CellGrid(CellGridBuilder<T> builder) {
        super(builder.getColumns(), builder.getRows());
        cells = builder.build();
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                cells[column][row].draw(spriteBatch);
            }
        }
    }

    @Override
    public void setColor(Color color) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                cells[column][row].setColor(color);
            }
        }
    }

    public void setCellSize(float size) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                cells[column][row].setPosition(column * size, row * size);
                cells[column][row].setSize(size);
            }
        }
    }

    public void translate(float x, float y) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                cells[column][row].translate(x, y);
            }
        }
    }

    public float getWidth() {
        return columns * cells[0][0].getWidth();
    }

    public float getHeight() {
        return rows * cells[0][0].getHeight();
    }
}