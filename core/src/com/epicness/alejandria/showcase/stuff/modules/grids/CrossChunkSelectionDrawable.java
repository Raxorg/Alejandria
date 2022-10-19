package com.epicness.alejandria.showcase.stuff.modules.grids;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CELL_SIZE;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CHUNK_DIMENSION;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CHUNK_SIZE;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.GRID_DIMENSION;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.GRID_X;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.GRID_Y;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;

import java.util.ArrayList;
import java.util.List;

public class CrossChunkSelectionDrawable implements Drawable {

    private final RectangleCell background;
    private final List<Chunk> chunks;

    public CrossChunkSelectionDrawable() {
        background = new RectangleCell(GRID_X, GRID_Y, SHOWCASE_SIZE, WHITE);

        chunks = new ArrayList<>();
        for (int column = 0; column < GRID_DIMENSION; column++) {
            for (int row = 0; row < GRID_DIMENSION; row++) {
                chunks.add(new Chunk(
                        GRID_X + column * CHUNK_SIZE, GRID_Y + row * CHUNK_SIZE,
                        CHUNK_SIZE, CHUNK_SIZE,
                        0, CHUNK_DIMENSION, CELL_SIZE)
                );
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(Filled);
        background.draw(shapeRenderer);
        for (int i = 0; i < chunks.size(); i++) {
            chunks.get(i).drawCells(shapeRenderer);
        }
        shapeRenderer.set(Line);
        for (int i = 0; i < chunks.size(); i++) {
            chunks.get(i).drawBounds(shapeRenderer);
        }
        shapeRenderer.end();
    }

    public RectangleCell getBackground() {
        return background;
    }

    public List<Chunk> getChunks() {
        return chunks;
    }
}