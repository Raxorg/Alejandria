package com.epicness.alejandria.showcase.stuff.modules.grids;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.epicness.fundamentals.utils.Random;

import java.util.ArrayList;
import java.util.List;

public class Chunk {

    public final Rectangle bounds;
    public final List<RectangleCell> cells;

    public Chunk(float x, float y, float w, float h, float spacing, int dimension, float cellSize) {
        bounds = new Rectangle(x, y, w, h);
        cells = new ArrayList<>();
        Color chunkColor = Random.opaqueColor();
        for (int column = 0; column < dimension; column++) {
            for (int row = 0; row < dimension; row++) {
                Color cellColor = chunkColor.cpy().lerp(BLACK, MathUtils.random(0.2f, 0.7f));
                float cellX = x + column * (cellSize + spacing);
                float cellY = y + row * (cellSize + spacing);
                cells.add(new RectangleCell(cellX, cellY, cellSize, cellColor));
            }
        }
    }

    public void drawCells(ShapeRenderer shapeRenderer) {
        for (int i = 0; i < cells.size(); i++) {
            RectangleCell cell = cells.get(i);
            shapeRenderer.setColor(cell.color);
            shapeRenderer.rect(cell.x, cell.y, cell.width, cell.height);
        }
    }

    public void drawBounds(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(WHITE);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}