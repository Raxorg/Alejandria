package com.epicness.fundamentals.utils;

import com.badlogic.gdx.utils.SnapshotArray;

import java.util.function.Consumer;

public class ArrayUtils {

    public static <T> void loopArray(SnapshotArray<T> snapArray, Consumer<T> consumer) {
        T[] array = snapArray.begin();
        for (int i = 0, n = snapArray.size; i < n; i++) {
            consumer.accept(array[i]);
        }
        snapArray.end();
    }

    public static <T> void loopArray(T[] array, Consumer<T> consumer) {
        for (int i = 0; i < array.length; i++) {
            consumer.accept(array[i]);
        }
    }

    public static <T> T[][] rotateMatrix(T[][] matrix) {
        int cols = matrix[0].length;
        int rows = matrix.length;
        T[][] rotated = newMatrix(matrix[0][0], cols, rows);

        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                rotated[col][rows - 1 - row] = matrix[row][col];
            }
        }

        return rotated;
    }

    @SuppressWarnings("unchecked")
    private static <T> T[][] newMatrix(T sample, int cols, int rows) {
        if (sample instanceof Byte) {
            return (T[][]) new Byte[cols][rows];
        } else if (sample instanceof Short) {
            return (T[][]) new Short[cols][rows];
        } else if (sample instanceof Integer) {
            return (T[][]) new Integer[cols][rows];
        } else if (sample instanceof Character) {
            return (T[][]) new Character[cols][rows];
        } else {
            throw new IllegalArgumentException("Class not supported");
        }
    }
}