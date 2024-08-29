package com.epicness.fundamentals.stuff.grid;

import java.util.List;

public class AStarGrid<T> extends GenericGrid<AStarCostCell<T>> {

    public AStarGrid(int cols, int rows) {
        super(cols, rows, AStarCostCell::new);
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                findNeighbors(c, r);
            }
        }
    }

    private void findNeighbors(int col, int row) {
        List<AStarCostCell<T>> neighbors = cells.get(col).get(row).neighbors;

        if (col - 1 >= 0) {
            // Left
            neighbors.add(cells.get(col - 1).get(row));
            // Left Down
            if (row - 1 >= 0) neighbors.add(cells.get(col - 1).get(row - 1));
            // Left Up
            if (row + 1 < rows) neighbors.add(cells.get(col - 1).get(row + 1));
        }

        if (col + 1 < cols) {
            // Right
            neighbors.add(cells.get(col + 1).get(row));
            // Right Down
            if (row - 1 >= 0) neighbors.add(cells.get(col + 1).get(row - 1));
            // Right Up
            if (row + 1 < rows) neighbors.add(cells.get(col + 1).get(row + 1));
        }

        // Down
        if (row - 1 >= 0) neighbors.add(cells.get(col).get(row - 1));
        // Up
        if (row + 1 < rows) neighbors.add(cells.get(col).get(row + 1));
    }

    public T getObject(int col, int row) {
        return cells.get(col).get(row).getObject();
    }
}