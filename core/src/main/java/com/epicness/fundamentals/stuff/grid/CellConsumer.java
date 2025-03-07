package com.epicness.fundamentals.stuff.grid;

@FunctionalInterface
public interface CellConsumer<T> {

    void accept(int col, int row, T cell);
}