package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Grid {

    protected final int columns, rows;
    private final Cell[][] cells;

    public Grid(int columns, int rows, Sprite cellSprite) {
        this.columns = columns;
        this.rows = rows;
        cells = new Cell[columns][];
        for (int column = 0; column < columns; column++) {
            cells[column] = new Cell[rows];
            for (int row = 0; row < rows; row++) {
                cells[column][row] = new Cell(cellSprite, column, row);
            }
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                cells[column][row].draw(spriteBatch);
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

    public Cell[][] getCells() {
        return cells;
    }
}