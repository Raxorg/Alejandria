package com.epicness.alejandria.showcase.stuff.modules.grids;

import static com.badlogic.gdx.graphics.Color.BLACK;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.RectanglePlus;
import com.epicness.fundamentals.utils.Random;

import java.util.ArrayList;
import java.util.List;

public class Chunk {

    public final RectanglePlus bounds;
    public final List<RectanglePlus> cells;

    public Chunk(float x, float y, float w, float h, float spacing, int dimension, float cellSize) {
        bounds = new RectanglePlus(x, y, w, h);
        cells = new ArrayList<>();
        Color chunkColor = Random.opaqueColor();
        for (int column = 0; column < dimension; column++) {
            for (int row = 0; row < dimension; row++) {
                Color cellColor = chunkColor.cpy().lerp(BLACK, MathUtils.random(0.2f, 0.7f));
                float cellX = x + column * (cellSize + spacing);
                float cellY = y + row * (cellSize + spacing);
                cells.add(new RectanglePlus(cellX, cellY, cellSize, cellColor));
            }
        }
    }

    public void drawCells(ShapeDrawerPlus shapeDrawer) {
        for (int i = 0; i < cells.size(); i++) {
            cells.get(i).draw(shapeDrawer);
        }
    }

    public void drawBounds(ShapeDrawerPlus shapeDrawer) {
        bounds.drawBorder(shapeDrawer);
    }
}