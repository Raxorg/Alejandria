package com.epicness.alejandria.showcase.modules.pathfinding;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.FOREST;
import static com.badlogic.gdx.graphics.Color.ROYAL;
import static com.epicness.alejandria.showcase.constants.AStarConstants.MOVE_DIAGONALLY_COST;
import static com.epicness.alejandria.showcase.constants.AStarConstants.MOVE_STRAIGHT_COST;
import static com.epicness.fundamentals.constants.SharedConstants.DARK_DIRT;
import static com.epicness.fundamentals.constants.SharedConstants.DARK_GRASS;
import static com.epicness.fundamentals.utils.TextUtils.copyOf;

import com.badlogic.gdx.Input;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.AStarCostCell;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.GenericGrid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlternativeAStar extends Module<AlternativeAStarDrawable> {

    private GenericGrid<AStarCostCell> grid;

    public AlternativeAStar() {
        super("Alternative A Star", "Based on Code Monkey's tutorial\n\nLeft click to pathfind\n\nRight click to toggle obstacles");
    }

    @Override
    public AlternativeAStarDrawable setup() {
        drawable = new AlternativeAStarDrawable(sharedAssets.getSquare32(), copyOf(sharedAssets.getTimesSquare()));
        grid = drawable.getGrid();
        return drawable;
    }

    @Override
    public void touchDown(float x, float y, int button) {
        AStarCostCell cell;
        if (button == Input.Buttons.RIGHT) {
            cell = grid.getCellAtPosition(x, y);
            cell.enabled = !cell.enabled;
            cell.setColor(cell.enabled ? DARK_DIRT : BLACK);
            return;
        }

        for (int c = 0; c < grid.cols; c++) {
            for (int r = 0; r < grid.rows; r++) {
                cell = grid.getCell(c, r);
                cell.setCosts(0, 0, 0);
                cell.setColor(cell.enabled ? DARK_DIRT : BLACK);
            }
        }

        cell = grid.getCellAtPosition(x, y);
        if (cell == null) return;

        List<AStarCostCell> path = findPath(0, 0, cell.col, cell.row);
        if (path == null) return;

        for (int i = 0; i < path.size(); i++) {
            path.get(i).setColor(DARK_GRASS);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private List<AStarCostCell> findPath(int startCol, int startRow, int endCol, int endRow) {
        AStarCostCell startCell = grid.getCell(startCol, startRow);
        AStarCostCell endCell = grid.getCell(endCol, endRow);
        List<AStarCostCell> openList = new ArrayList<>();
        openList.add(startCell);
        List<AStarCostCell> closedList = new ArrayList<>();

        for (int c = 0; c < grid.cols; c++) {
            for (int r = 0; r < grid.rows; r++) {
                AStarCostCell cell = grid.getCell(c, r);
                cell.gCost = Integer.MAX_VALUE;
                cell.fCost = cell.gCost + cell.hCost;
                cell.previousCell = null;
            }
        }

        startCell.gCost = 0;
        startCell.hCost = calculateHCost(startCell, endCell);
        startCell.fCost = startCell.gCost + startCell.hCost;
        startCell.setCosts(startCell.gCost, startCell.hCost, startCell.fCost);

        while (openList.size() > 0) {
            AStarCostCell currentCell = getLowestFCostCell(openList);
            if (currentCell == endCell) {
                return calculatePath(endCell);
            }

            openList.remove(currentCell);
            closedList.add(currentCell);
            currentCell.setColor(FOREST);

            List<AStarCostCell> neighbors = getNeighbors(currentCell);
            for (int i = 0; i < neighbors.size(); i++) {
                AStarCostCell neighbor = neighbors.get(i);
                if (closedList.contains(neighbor)) continue;
                if (!neighbor.enabled) {
                    closedList.add(neighbor);
                    continue;
                }

                int tentativeGCost = currentCell.gCost + calculateHCost(currentCell, neighbor);
                if (tentativeGCost < neighbor.gCost) {
                    neighbor.previousCell = currentCell;
                    neighbor.gCost = tentativeGCost;
                    neighbor.hCost = calculateHCost(neighbor, endCell);
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;
                    neighbor.setCosts(neighbor.gCost, neighbor.hCost, neighbor.fCost);

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                        neighbor.setColor(ROYAL);
                    }
                }
            }
        }
        return null;
    }

    private int calculateHCost(AStarCostCell a, AStarCostCell b) {
        int xDistance = Math.abs(a.col - b.col);
        int yDistance = Math.abs(a.row - b.row);
        int remaining = Math.abs(xDistance - yDistance);
        return MOVE_DIAGONALLY_COST * Math.min(xDistance, yDistance) + MOVE_STRAIGHT_COST * remaining;
    }

    private AStarCostCell getLowestFCostCell(List<AStarCostCell> cellList) {
        AStarCostCell lowestFCostCell = cellList.get(0);
        for (int i = 0; i < cellList.size(); i++) {
            if (cellList.get(i).fCost < lowestFCostCell.fCost) {
                lowestFCostCell = cellList.get(i);
            }
        }
        return lowestFCostCell;
    }

    private List<AStarCostCell> calculatePath(AStarCostCell endCell) {
        List<AStarCostCell> path = new ArrayList<>();
        path.add(endCell);
        AStarCostCell currentCell = endCell;
        while (currentCell.previousCell != null) {
            path.add(currentCell.previousCell);
            currentCell = currentCell.previousCell;
        }
        Collections.reverse(path);
        return path;
    }

    private List<AStarCostCell> getNeighbors(AStarCostCell cell) {
        List<AStarCostCell> neighbors = new ArrayList<>();

        if (cell.col - 1 >= 0) {
            // Left
            neighbors.add(grid.getCell(cell.col - 1, cell.row));
            // Left Down
            if (cell.row - 1 >= 0) neighbors.add(grid.getCell(cell.col - 1, cell.row - 1));
            // Left Up
            if (cell.row + 1 < grid.rows) neighbors.add(grid.getCell(cell.col - 1, cell.row + 1));
        }
        if (cell.col + 1 < grid.cols) {
            // Right
            neighbors.add(grid.getCell(cell.col + 1, cell.row));
            // Right Down
            if (cell.row - 1 >= 0) neighbors.add(grid.getCell(cell.col + 1, cell.row - 1));
            // Right Up
            if (cell.row + 1 < grid.rows) neighbors.add(grid.getCell(cell.col + 1, cell.row + 1));
        }
        // Down
        if (cell.row - 1 >= 0) neighbors.add(grid.getCell(cell.col, cell.row - 1));
        // Up
        if (cell.row + 1 < grid.rows) neighbors.add(grid.getCell(cell.col, cell.row + 1));

        return neighbors;
    }
}