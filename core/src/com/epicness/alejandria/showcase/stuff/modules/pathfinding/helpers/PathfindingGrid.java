package com.epicness.alejandria.showcase.stuff.modules.pathfinding.helpers;

import static com.epicness.alejandria.showcase.constants.AStarConstants.GRID_COLUMNS;
import static com.epicness.alejandria.showcase.constants.AStarConstants.GRID_ROWS;
import static com.epicness.fundamentals.SharedConstants.PIXEL_PATH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.List;

public class PathfindingGrid {

    private final PathfindingCell[][] cells;

    public PathfindingGrid() {
        int columns = GRID_COLUMNS, rows = GRID_ROWS;
        float cellSize = (Gdx.graphics.getWidth() - columns) / (float) columns;
        Sprite cellSprite = new Sprite(new Texture(Gdx.files.internal(PIXEL_PATH)));
        cells = new PathfindingCell[columns][];

        for (int column = 0; column < columns; column++) {
            cells[column] = new PathfindingCell[rows];
            for (int row = 0; row < rows; row++) {
                cells[column][row] = new PathfindingCell(column, row, cellSprite, cellSize);
                cells[column][row].setPosition(column * cellSize + column, row * cellSize + row);
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
        ScreenUtils.clear(Color.BLACK);
        spriteBatch.begin();
        for (int column = 0; column < GRID_COLUMNS; column++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                cells[column][row].draw(spriteBatch);
            }
        }
        spriteBatch.end();
    }

    public PathfindingCell[][] getCells() {
        return cells;
    }
}