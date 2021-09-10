package com.epicness.alejandria.module.stuff.modules.pathfinding;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.epicness.alejandria.module.stuff.modules.Module;

import java.util.ArrayList;

import static com.badlogic.gdx.Input.Keys.NUM_1;
import static com.badlogic.gdx.Input.Keys.NUM_2;
import static com.epicness.alejandria.ModuleID.ASTAR;

public class AStar extends Module {

    private ArrayList<Cell> openCells, closedCells, obstacleCells;
    private Grid grid;
    private Cell start, target;
    private float time, interval;
    private boolean finished;

    public AStar() {
        super(ASTAR);
    }

    @Override
    public void setup() {
        openCells = new ArrayList<>();
        closedCells = new ArrayList<>();
        obstacleCells = new ArrayList<>();
        grid = new Grid();
        start = grid.cells[10][5];
        start.sprite.setColor(Color.GREEN);
        target = grid.cells[30][40];
        target.sprite.setColor(Color.RED);
        openCells.add(start);
        obstacleCells.remove(start);
        obstacleCells.remove(target);
        time = 0f;
        interval = 0.03f;
        finished = false;
    }

    @Override
    public void update(float delta) {
        time += delta;
        if (time >= interval) {
            step();
            time = 0f;
        }
        if (Gdx.input.isKeyJustPressed(NUM_1)) {
            setup();
        }
        if (Gdx.input.isKeyJustPressed(NUM_2)) {
            interval = interval == 1f ? 0.03f : 1f;
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        grid.draw(spriteBatch);
    }

    private void step() {
        if (finished) {
            return;
        }
        if (openCells.isEmpty()) {
            System.out.println("NO PATH");
            finished = true;
            return;
        }
        Cell bestCell = findBestCell();
        checkSuccess(bestCell);
        openCells.remove(bestCell);
        closedCells.add(bestCell);
        handleNeighbors(bestCell);
        tintCells(bestCell);
    }

    private Cell findBestCell() {
        Cell bestCell = openCells.get(0);
        for (int i = 0; i < openCells.size(); i++) {
            if (bestCell.distance > openCells.get(i).distance) {
                bestCell = openCells.get(i);
            }
        }
        return bestCell;
    }

    private void tintCells(Cell bestCell) {
        for (int i = 0; i < closedCells.size(); i++) {
            closedCells.get(i).sprite.setColor(Color.TAN);
        }
        while (bestCell.previous != null) {
            bestCell.sprite.setColor(Color.CHARTREUSE);
            bestCell = bestCell.previous;
        }
        start.sprite.setColor(Color.BLUE);
        target.sprite.setColor(Color.RED);
    }

    private void checkSuccess(Cell bestCell) {
        if (bestCell == target) {
            finished = true;
        }
    }

    private void handleNeighbors(Cell bestCell) {
        for (int i = 0; i < bestCell.neighbors.size(); i++) {
            Cell neighbor = bestCell.neighbors.get(i);
            if (closedCells.contains(neighbor) || obstacleCells.contains(neighbor)) {
                continue;
            }
            // Adding 1 instead of the euclidean heuristic yields faster results
            float tentativeDistance = bestCell.distanceFromStart + euclideanHeuristic(bestCell, neighbor);
            if (!openCells.contains(neighbor)) {
                openCells.add(neighbor);
                neighbor.sprite.setColor(Color.ORANGE);
            } else if (tentativeDistance >= neighbor.distanceFromStart) {
                continue;
            }
            neighbor.distanceFromStart = tentativeDistance;
            neighbor.distance = neighbor.distanceFromStart + euclideanHeuristic(neighbor, target);
            neighbor.previous = bestCell;
        }
    }

    private float euclideanHeuristic(Cell start, Cell end) {
        // Takes diagonals into account
        return new Vector2(start.column, start.row).dst(new Vector2(end.column, end.row));
    }

    private float manhattanHeuristic(Cell start, Cell end) {
        // Doesn't take diagonals into account
        return Math.abs(start.column - end.column) + Math.abs(start.row - end.row);
    }

    private class Grid {

        private final Cell[][] cells;
        private final int size = 50;
        private final int columns = size, rows = size;

        private Grid() {
            float cellSize = (Gdx.graphics.getWidth() - columns) / (float) columns;
            Sprite cellSprite = new Sprite(new Texture(Gdx.files.internal("images/pixel.png")));
            cells = new Cell[columns][];
            for (int column = 0; column < columns; column++) {
                cells[column] = new Cell[rows];
                for (int row = 0; row < rows; row++) {
                    cells[column][row] = new Cell(column, row, cellSprite, cellSize);
                    cells[column][row].sprite.setPosition(column * cellSize + column, row * cellSize + row);
                    if (MathUtils.randomBoolean(0.1f)) {
                        cells[column][row].sprite.setColor(Color.BLACK);
                        obstacleCells.add(cells[column][row]);
                    }
                }
            }
            for (int column = 0; column < columns; column++) {
                for (int row = 0; row < rows; row++) {
                    if (column > 0) {
                        cells[column][row].neighbors.add(cells[column - 1][row]);
                        if (row > 0) {
                            cells[column][row].neighbors.add(cells[column - 1][row - 1]);
                        }
                    }
                    if (row > 0) {
                        cells[column][row].neighbors.add(cells[column][row - 1]);
                        if (column < columns - 1) {
                            cells[column][row].neighbors.add(cells[column + 1][row - 1]);
                        }
                    }
                    if (column < columns - 1) {
                        cells[column][row].neighbors.add(cells[column + 1][row]);
                        if (row < rows - 1) {
                            cells[column][row].neighbors.add(cells[column + 1][row + 1]);
                        }
                    }
                    if (row < rows - 1) {
                        cells[column][row].neighbors.add(cells[column][row + 1]);
                        if (column > 0) {
                            cells[column][row].neighbors.add(cells[column - 1][row + 1]);
                        }
                    }
                }
            }
        }

        private void draw(SpriteBatch spriteBatch) {
            for (int column = 0; column < columns; column++) {
                for (int row = 0; row < rows; row++) {
                    cells[column][row].draw(spriteBatch);
                }
            }
        }
    }

    private static class Cell {

        private final int column, row;
        private final ArrayList<Cell> neighbors;
        private Cell previous;
        private final Sprite sprite;
        private float distance, distanceFromStart;

        private Cell(int column, int row, Sprite cellSprite, float size) {
            this.column = column;
            this.row = row;
            neighbors = new ArrayList<>();
            sprite = new Sprite(cellSprite);
            sprite.setSize(size, size);
        }

        private void draw(SpriteBatch spriteBatch) {
            sprite.draw(spriteBatch);
        }
    }
}