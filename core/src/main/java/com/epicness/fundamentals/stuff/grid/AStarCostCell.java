package com.epicness.fundamentals.stuff.grid;

import java.util.ArrayList;
import java.util.List;

public class AStarCostCell<T> {

    private T object;
    public final int col, row;
    public int gCost, hCost, fCost;
    public AStarCostCell<T> previousCell;
    public final List<AStarCostCell<T>> neighbors;
    public boolean blocked;

    public AStarCostCell(int col, int row) {
        this.col = col;
        this.row = row;
        neighbors = new ArrayList<>();
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public boolean isEmpty() {
        return object == null;
    }
}