package com.epicness.alejandria.showcase.modules.grids;

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
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.alejandria.showcase.stuff.modules.grids.Chunk;
import com.epicness.alejandria.showcase.stuff.modules.grids.RectangleCell;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

import java.util.ArrayList;
import java.util.List;

public class CrossChunkSelectionDrawable implements ModuleDrawable {

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
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
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

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        for (int i = 0; i < chunks.size(); i++) {
            chunks.get(i).drawBounds(shapeRenderer);
        }
    }

    public RectangleCell getBackground() {
        return background;
    }

    public List<Chunk> getChunks() {
        return chunks;
    }
}