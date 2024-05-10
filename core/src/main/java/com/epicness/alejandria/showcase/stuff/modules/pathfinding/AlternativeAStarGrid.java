package com.epicness.alejandria.showcase.stuff.modules.pathfinding;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AlternativeAStarGrid {

    public final int cols, rows;
    private final AlternativeAStarCell[][] cells;

    public AlternativeAStarGrid(int cols, int rows, float cellSize, Sprite pixel, BitmapFont font, BitmapFont smallerFont) {
        this.cols = cols;
        this.rows = rows;
        cells = new AlternativeAStarCell[cols][rows];
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                cells[c][r] = new AlternativeAStarCell(c, r, pixel, font, smallerFont);
                cells[c][r].setSize(cellSize);
                cells[c][r].setPosition(c * cellSize, r * cellSize);
                cells[c][r].setCosts(0, 0, 0);
            }
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                cells[c][r].draw(spriteBatch);
            }
        }
    }

    public void translateY(float amount) {
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                cells[c][r].translateY(amount);
            }
        }
    }

    public AlternativeAStarCell getCell(int col, int row) {
        return cells[col][row];
    }

    public AlternativeAStarCell getCellAtPosition(float x, float y) {
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if (cells[c][r].contains(x, y)) {
                    return cells[c][r];
                }
            }
        }
        return null;
    }
}