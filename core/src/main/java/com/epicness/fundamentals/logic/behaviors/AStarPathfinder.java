package com.epicness.fundamentals.logic.behaviors;

import com.epicness.fundamentals.stuff.grid.AStarCostCell;
import com.epicness.fundamentals.stuff.grid.AStarGrid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStarPathfinder<T> {

    private AStarGrid<T> grid;
    private final int diagonalCost, straightCost;

    public AStarPathfinder(int diagonalCost, int straightCost) {
        this.diagonalCost = diagonalCost;
        this.straightCost = straightCost;
    }

    public List<AStarCostCell<T>> findPath(int startCol, int startRow, int endCol, int endRow) {
        AStarCostCell<T> startCell = grid.getCell(startCol, startRow);
        AStarCostCell<T> endCell = grid.getCell(endCol, endRow);
        List<AStarCostCell<T>> openList = new ArrayList<>();
        List<AStarCostCell<T>> closedList = new ArrayList<>();

        AStarCostCell<T> cell;
        for (int c = 0; c < grid.cols; c++) {
            for (int r = 0; r < grid.rows; r++) {
                cell = grid.getCell(c, r);
                cell.gCost = Integer.MAX_VALUE;
                cell.fCost = cell.gCost + cell.hCost;
                cell.previousCell = null;
            }
        }

        openList.add(startCell);
        startCell.gCost = 0;
        startCell.hCost = calculateHCost(startCell, endCell);
        startCell.fCost = startCell.gCost + startCell.hCost;

        while (openList.size() > 0) {
            cell = getLowestFCostCell(openList);
            if (cell == endCell) return calculatePath(endCell);

            openList.remove(cell);
            closedList.add(cell);

            for (int i = 0; i < cell.neighbors.size(); i++) {
                AStarCostCell<T> neighbor = cell.neighbors.get(i);
                if (closedList.contains(neighbor)) continue;
                if (neighbor.blocked) {
                    closedList.add(neighbor);
                    continue;
                }

                int tentativeGCost = cell.gCost + calculateHCost(cell, neighbor);
                if (tentativeGCost < neighbor.gCost) {
                    neighbor.previousCell = cell;
                    neighbor.gCost = tentativeGCost;
                    neighbor.hCost = calculateHCost(neighbor, endCell);
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    private int calculateHCost(AStarCostCell<T> a, AStarCostCell<?> b) {
        int xDistance = Math.abs(a.col - b.col);
        int yDistance = Math.abs(a.row - b.row);
        int remaining = Math.abs(xDistance - yDistance);
        return diagonalCost * Math.min(xDistance, yDistance) + straightCost * remaining;
    }

    private AStarCostCell<T> getLowestFCostCell(List<AStarCostCell<T>> cellList) {
        AStarCostCell<T> lowestFCostCell = cellList.get(0);
        for (int i = 0; i < cellList.size(); i++) {
            if (cellList.get(i).fCost < lowestFCostCell.fCost) {
                lowestFCostCell = cellList.get(i);
            }
        }
        return lowestFCostCell;
    }

    private List<AStarCostCell<T>> calculatePath(AStarCostCell<T> endCell) {
        List<AStarCostCell<T>> path = new ArrayList<>();
        path.add(endCell);
        AStarCostCell<T> currentCell = endCell;
        while (currentCell.previousCell != null) {
            path.add(currentCell.previousCell);
            currentCell = currentCell.previousCell;
        }
        Collections.reverse(path);
        return path;
    }

    public void setGrid(AStarGrid<T> grid) {
        this.grid = grid;
    }
}