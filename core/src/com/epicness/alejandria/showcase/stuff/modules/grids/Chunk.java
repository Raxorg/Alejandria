package com.epicness.alejandria.showcase.stuff.modules.grids;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Chunk {

    public final Rectangle bounds;
    public final List<Rectangle> cells;

    public Chunk(float x, float y, float w, float h, float spacing, int dimension) {
        bounds = new Rectangle(x, y, w, h);
        cells = new ArrayList<>();
        float cellWidth = (w - (spacing * (dimension - 1))) / dimension;
        float cellHeight = (h - (spacing * (dimension - 1))) / dimension;
        for (int column = 0; column < dimension; column++) {
            for (int row = 0; row < dimension; row++) {
                float cellX = x + column * (cellWidth + spacing);
                float cellY = y + row * (cellHeight + spacing);
                cells.add(new Rectangle(cellX, cellY, cellWidth, cellHeight));
            }
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        for (int i = 0; i < cells.size(); i++) {
            Rectangle cell = cells.get(i);
            float progress = (float) i / (cells.size() - 1);
            shapeRenderer.setColor(WHITE.cpy().lerp(BLACK, progress));
            shapeRenderer.rect(cell.x, cell.y, cell.width, cell.height);
        }
        shapeRenderer.setColor(WHITE);
    }
}