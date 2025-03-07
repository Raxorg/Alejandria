package com.epicness.fundamentals.stuff.grid;

import java.util.List;
import java.util.function.Consumer;

public class AStarGrid<T> extends GenericGrid<AStarCostCell<T>> {

    public AStarGrid(int cols, int rows) {
        super(cols, rows, AStarCostCell::new);
        findNeighbors();
    }

    @Override
    public void setDimensions(int cols, int rows) {
        super.setDimensions(cols, rows);
        findNeighbors();
    }

    private void findNeighbors() {
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                findNeighbors(col, row);
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
        return getCell(col, row).getObject();
    }

    public final void traverseObjects(Consumer<T> consumer) {
        traverseCells(cell -> consumer.accept(cell.getObject()));
    }
}