package com.epicness.alejandria.showcase.modules.fun;

import static com.badlogic.gdx.graphics.Color.CLEAR;
import static com.epicness.alejandria.showcase.constants.FunConstants.SAND_DIMENSION;
import static com.epicness.alejandria.showcase.constants.FunConstants.SAND_UPDATE_RATE;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Rectangle;

public class FallingSand extends Module<FallingSandDrawable> {

    private boolean[][] grid;
    private Rectangle[][] sandMatrix;
    private float sandTimer;
    private boolean spawningSand;
    private float mouseX, mouseY;
    private float hue;

    public FallingSand() {
        super(
            "Falling Sand",
            "Who doesn't love falling sand! :D\n\n" +
                "Click or drag to spawn sand\n\n" +
                "Space to clear the sand"
        );
    }

    @Override
    protected FallingSandDrawable setup() {
        drawable = new FallingSandDrawable();

        grid = new boolean[SAND_DIMENSION][];
        for (int column = 0; column < SAND_DIMENSION; column++) {
            grid[column] = new boolean[SAND_DIMENSION];
            for (int row = 0; row < SAND_DIMENSION; row++) {
                grid[column][row] = false;
            }
        }

        sandMatrix = drawable.getSandMatrix();

        return drawable;
    }

    @Override
    public void update(float delta) {
        if (spawningSand) {
            handleSandSpawning();
        }

        sandTimer += delta;
        if (sandTimer >= SAND_UPDATE_RATE) {
            evaluateSand();
            sandTimer = 0f;
        }
    }

    private void handleSandSpawning() {
        for (int column = 0; column < SAND_DIMENSION; column++) {
            for (int row = 0; row < SAND_DIMENSION; row++) {
                if (sandMatrix[column][row].contains(mouseX, mouseY)) {
                    spawnSand(column, row, new Color(1f, 1f, 1f, 1f).fromHsv(hue, 1f, 1f));
                    hue += 0.25f;
                    hue %= 360f;
                    return;
                }
            }
        }
    }

    private void evaluateSand() {
        float diagonalChance;
        boolean canDiagonal;
        for (int row = 1; row < SAND_DIMENSION; row++) {
            for (int column = 0; column < SAND_DIMENSION; column++) {
                if (!grid[column][row]) continue;

                if (!grid[column][row - 1]) {
                    spawnSand(column, row - 1, sandMatrix[column][row].borderColor);
                    clearSand(column, row);
                    continue;
                }

                canDiagonal = false;
                diagonalChance = 0.5f;
                if (column > 0 && !grid[column - 1][row - 1]) {
                    diagonalChance += 0.5f;
                    canDiagonal = true;
                }

                if (column < SAND_DIMENSION - 1 && !grid[column + 1][row - 1]) {
                    diagonalChance -= 0.5f;
                    canDiagonal = true;
                }

                if (!canDiagonal) continue;

                if (MathUtils.randomBoolean(diagonalChance)) {
                    spawnSand(column - 1, row - 1, sandMatrix[column][row].borderColor);
                    clearSand(column, row);
                } else {
                    spawnSand(column + 1, row - 1, sandMatrix[column][row].borderColor);
                    clearSand(column, row);
                }
            }
        }
    }

    private void clearSand(int column, int row) {
        grid[column][row] = false;
        sandMatrix[column][row].borderColor.set(CLEAR);
    }

    private void spawnSand(int column, int row, Color color) {
        grid[column][row] = true;
        sandMatrix[column][row].borderColor.set(color);
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            for (int column = 0; column < SAND_DIMENSION; column++) {
                for (int row = 0; row < SAND_DIMENSION; row++) {
                    grid[column][row] = false;
                    sandMatrix[column][row].fillColor.set(CLEAR);
                }
            }
        }
    }

    @Override
    public void touchDown(float x, float y) {
        mouseX = x;
        mouseY = y;
        spawningSand = true;
    }

    @Override
    public void touchDragged(float x, float y) {
        mouseX = x;
        mouseY = y;
        spawningSand = true;
    }

    @Override
    public void touchUp(float x, float y) {
        spawningSand = false;
    }
}