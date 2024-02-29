package com.epicness.alejandria.showcase.stuff.modules.grids;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Rectangle;
import com.epicness.fundamentals.utils.Random;

import java.util.ArrayList;
import java.util.List;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Chunk {

    public final Rectangle bounds;
    public final List<Rectangle> cells;

    public Chunk(float x, float y, float w, float h, float spacing, int dimension, float cellSize) {
        bounds = new Rectangle(x, y, w, h);
        cells = new ArrayList<>();
        Color chunkColor = Random.opaqueColor();
        for (int column = 0; column < dimension; column++) {
            for (int row = 0; row < dimension; row++) {
                Color cellColor = chunkColor.cpy().lerp(BLACK, MathUtils.random(0.2f, 0.7f));
                float cellX = x + column * (cellSize + spacing);
                float cellY = y + row * (cellSize + spacing);
                cells.add(new Rectangle(cellX, cellY, cellSize, cellColor));
            }
        }
    }

    public void drawCells(ShapeDrawer shapeDrawer) {
        for (int i = 0; i < cells.size(); i++) {
            cells.get(i).draw(shapeDrawer);
        }
    }

    public void drawBounds(ShapeDrawer shapeDrawer) {
        shapeDrawer.rectangle(bounds, WHITE);
    }
}