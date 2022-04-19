package com.epicness.alejandria.showcase.logic.modules.pathfinding;

import static com.badlogic.gdx.Input.Keys.NUM_1;
import static com.badlogic.gdx.Input.Keys.NUM_2;
import static com.epicness.alejandria.showcase.constants.AStarConstants.GRID_COLUMNS;
import static com.epicness.alejandria.showcase.constants.AStarConstants.GRID_ROWS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.AStarDrawable;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.helpers.PathfindingCell;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.helpers.PathfindingGrid;

import java.util.ArrayList;
import java.util.List;

public class AStar extends Module {

    private AStarDrawable drawable;
    // Logic
    private List<PathfindingCell> openCells, closedCells, obstacleCells;
    private PathfindingCell start, target;
    private float time, interval;
    private boolean finished;

    public AStar() {
        super("A Star Pathfinding");
    }

    @Override
    public void setup() {
        drawable = new AStarDrawable();
        stuff.getShowcase().setDrawable(drawable);

        PathfindingGrid grid = drawable.getGrid();
        PathfindingCell[][] cells = drawable.getGrid().getCells();
        for (int column = 0; column < GRID_COLUMNS; column++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                if (MathUtils.randomBoolean(0.1f)) {
                    cells[column][row].setColor(Color.BLACK);
                    obstacleCells.add(cells[column][row]);
                }
            }
        }

        openCells = new ArrayList<>();
        closedCells = new ArrayList<>();
        obstacleCells = new ArrayList<>();

        start = grid.getCells()[10][5];
        start.setColor(Color.GREEN);
        target = grid.getCells()[30][40];
        target.setColor(Color.RED);
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

    private void step() {
        if (finished) {
            return;
        }
        if (openCells.isEmpty()) {
            System.out.println("NO PATH");
            finished = true;
            return;
        }
        PathfindingCell bestCell = findBestCell();
        checkSuccess(bestCell);
        openCells.remove(bestCell);
        closedCells.add(bestCell);
        handleNeighbors(bestCell);
        tintCells(bestCell);
    }

    private PathfindingCell findBestCell() {
        PathfindingCell bestCell = openCells.get(0);
        for (int i = 0; i < openCells.size(); i++) {
            if (bestCell.getDistance() > openCells.get(i).getDistance()) {
                bestCell = openCells.get(i);
            }
        }
        return bestCell;
    }

    private void tintCells(PathfindingCell bestCell) {
        for (int i = 0; i < closedCells.size(); i++) {
            closedCells.get(i).setColor(Color.TAN);
        }
        while (bestCell.getPrevious() != null) {
            bestCell.setColor(Color.CHARTREUSE);
            bestCell = bestCell.getPrevious();
        }
        start.setColor(Color.BLUE);
        target.setColor(Color.RED);
    }

    private void checkSuccess(PathfindingCell bestCell) {
        if (bestCell == target) {
            finished = true;
        }
    }

    private void handleNeighbors(PathfindingCell bestCell) {
        for (int i = 0; i < bestCell.getNeighbors().size(); i++) {
            PathfindingCell neighbor = bestCell.getNeighbors().get(i);
            if (closedCells.contains(neighbor) || obstacleCells.contains(neighbor)) {
                continue;
            }
            // Adding 1 instead of the euclidean heuristic yields faster results
            float tentativeDistance = bestCell.getDistanceFromStart() + euclideanHeuristic(bestCell, neighbor);
            if (!openCells.contains(neighbor)) {
                openCells.add(neighbor);
                neighbor.setColor(Color.ORANGE);
            } else if (tentativeDistance >= neighbor.getDistanceFromStart()) {
                continue;
            }
            neighbor.setDistanceFromStart(tentativeDistance);
            neighbor.setDistance(neighbor.getDistanceFromStart() + euclideanHeuristic(neighbor, target));
            neighbor.setPrevious(bestCell);
        }
    }

    private float euclideanHeuristic(PathfindingCell start, PathfindingCell end) {
        // Takes diagonals into account
        return new Vector2(start.getColumn(), start.getRow()).dst(new Vector2(end.getColumn(), end.getRow()));
    }

    private float manhattanHeuristic(PathfindingCell start, PathfindingCell end) {
        // Doesn't take diagonals into account
        return Math.abs(start.getColumn() - end.getColumn()) + Math.abs(start.getRow() - end.getRow());
    }

    @Override
    public void exit() {
        drawable = null;
    }
}