package com.epicness.fundamentals.stuff.interfaces;

@FunctionalInterface
public interface ColRowSupplier<T> {

    T get(int col, int row);
}