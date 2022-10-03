package com.epicness.alejandria.showcase.stuff.modules.grids;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CELL_SIZE;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CHUNK_DIMENSION;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CHUNK_SIZE;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.DIMENSION;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;

import java.util.ArrayList;
import java.util.List;

public class CrossChunkSelectionDrawable implements Drawable {

    private final List<Chunk> chunks;

    public CrossChunkSelectionDrawable() {
        chunks = new ArrayList<>();
        for (int column = 0; column < DIMENSION; column++) {
            for (int row = 0; row < DIMENSION; row++) {
                chunks.add(new Chunk(
                        column * CHUNK_SIZE, row * CHUNK_SIZE,
                        CHUNK_SIZE, CHUNK_SIZE,
                        0, CHUNK_DIMENSION, CELL_SIZE)
                );
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(Filled);
        for (int i = 0; i < chunks.size(); i++) {
            chunks.get(i).drawCells(shapeRenderer);
        }
        shapeRenderer.set(Line);
        for (int i = 0; i < chunks.size(); i++) {
            chunks.get(i).drawBounds(shapeRenderer);
        }
        shapeRenderer.end();
    }

    public List<Chunk> getChunks() {
        return chunks;
    }
}