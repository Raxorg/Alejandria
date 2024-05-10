package com.epicness.alejandria.showcase.stuff.modules.pathfinding;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public class PathfindingGrid {

    private final PathfindingCell[][] cells;

    public PathfindingGrid(Sprite cellSprite, float cellSize, int columns, int rows) {
        cells = new PathfindingCell[columns][];

        for (int column = 0; column < columns; column++) {
            cells[column] = new PathfindingCell[rows];
            for (int row = 0; row < rows; row++) {
                cells[column][row] = new PathfindingCell(column, row, cellSprite, cellSize);
                cells[column][row].setPosition(column * cellSize, row * cellSize);
            }
        }
        List<PathfindingCell> neighbors;
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                neighbors = cells[column][row].getNeighbors();
                if (column > 0) {
                    neighbors.add(cells[column - 1][row]);
                    if (row > 0) {
                        neighbors.add(cells[column - 1][row - 1]);
                    }
                }
                if (row > 0) {
                    neighbors.add(cells[column][row - 1]);
                    if (column < columns - 1) {
                        neighbors.add(cells[column + 1][row - 1]);
                    }
                }
                if (column < columns - 1) {
                    neighbors.add(cells[column + 1][row]);
                    if (row < rows - 1) {
                        neighbors.add(cells[column + 1][row + 1]);
                    }
                }
                if (row < rows - 1) {
                    neighbors.add(cells[column][row + 1]);
                    if (column > 0) {
                        neighbors.add(cells[column - 1][row + 1]);
                    }
                }
            }
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int column = 0; column < cells.length; column++) {
            for (int row = 0; row < cells[column].length; row++) {
                cells[column][row].draw(spriteBatch);
            }
        }
    }

    public void setY(float y) {
        float gridY = cells[0][0].getY();
        for (int c = 0; c < cells.length; c++) {
            for (int r = 0; r < cells[c].length; r++) {
                cells[c][r].translateY(y - gridY);
            }
        }
    }

    public PathfindingCell[][] getCells() {
        return cells;
    }
}