package com.epicness.alejandria.showcase.modules.grids;

import static com.badlogic.gdx.graphics.Color.WHITE;
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
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.RectanglePlus;

import java.util.ArrayList;
import java.util.List;

public class CrossChunkSelectionDrawable implements ModuleDrawable {

    private final RectanglePlus background;
    private final List<Chunk> chunks;

    public CrossChunkSelectionDrawable() {
        background = new RectanglePlus(GRID_X, GRID_Y, SHOWCASE_SIZE, WHITE);

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
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        background.draw(shapeDrawer);
        for (int i = 0; i < chunks.size(); i++) {
            chunks.get(i).drawCells(shapeDrawer);
        }
        for (int i = 0; i < chunks.size(); i++) {
            chunks.get(i).drawBounds(shapeDrawer);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }

    public RectanglePlus getBackground() {
        return background;
    }

    public List<Chunk> getChunks() {
        return chunks;
    }
}