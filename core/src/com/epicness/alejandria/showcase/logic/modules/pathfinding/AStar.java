package com.epicness.alejandria.showcase.logic.modules.pathfinding;

import static com.epicness.alejandria.showcase.constants.AStarConstants.GRID_COLUMNS;
import static com.epicness.alejandria.showcase.constants.AStarConstants.GRID_ROWS;
import static com.epicness.fundamentals.SharedConstants.DARK_GRASS;
import static com.epicness.fundamentals.SharedConstants.DIRT;
import static com.epicness.fundamentals.SharedConstants.GRASS;
import static com.epicness.fundamentals.SharedConstants.LIGHT_DIRT;
import static com.epicness.fundamentals.SharedConstants.LIGHT_GRASS;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.epicness.alejandria.showcase.logic.input.AStarInput;
import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.AStarDrawable;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.helpers.PathfindingCell;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.helpers.PathfindingGrid;

import java.util.ArrayList;
import java.util.List;

public class AStar extends Module<AStarDrawable> {

    // Logic
    private List<PathfindingCell> openCells, closedCells, obstacleCells;
    private PathfindingCell start, target;
    private float time, interval;
    private boolean finished;

    public AStar() {
        super("A Star Pathfinding", "1 to reset, 2 to change simulation speed");
    }

    @Override
    public AStarDrawable setup() {
        logic.handler(ShowcaseInputHandler.class).setModuleInputHandler(
                logic.handler(AStarInput.class)
        );
        drawable = new AStarDrawable(sharedAssets.getSquare());

        initialize();

        return drawable;
    }

    public void initialize() {
        openCells = new ArrayList<>();
        closedCells = new ArrayList<>();
        obstacleCells = new ArrayList<>();

        PathfindingGrid grid = drawable.getGrid();
        PathfindingCell[][] cells = drawable.getGrid().getCells();
        for (int column = 0; column < GRID_COLUMNS; column++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                PathfindingCell cell = cells[column][row];
                cell.setColor(LIGHT_GRASS.cpy().lerp(DARK_GRASS, MathUtils.random(0f, 0.25f)));
                if (MathUtils.randomBoolean(0.15f)) {
                    cell.setColor(Color.BLACK);
                    obstacleCells.add(cell);
                }
            }
        }

        start = grid.getCells()[1][4];
        start.setColor(Color.BLUE);
        target = grid.getCells()[28][25];
        target.setColor(Color.RED);
        openCells.add(start);
        obstacleCells.remove(start);
        obstacleCells.remove(target);

        time = 0f;
        interval = 0.01f;
        finished = false;
    }

    public void toggleInterval() {
        interval = interval == 0.7f ? 0.01f : 0.7f;
    }

    @Override
    public void update(float delta) {
        time += delta;
        if (time >= interval) {
            step();
            time = 0f;
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
            closedCells.get(i).setColor(LIGHT_DIRT);
        }
        while (bestCell.getPrevious() != null) {
            bestCell.setColor(GRASS);
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
                neighbor.setColor(DIRT);
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
}