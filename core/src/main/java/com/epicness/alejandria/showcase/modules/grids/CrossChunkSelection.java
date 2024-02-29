package com.epicness.alejandria.showcase.modules.grids;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CELL_SIZE;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CHUNK_DIMENSION;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CHUNK_SIZE;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.GRID_DIMENSION;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.GRID_SIZE;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.GRID_X;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.GRID_Y;
import static com.epicness.fundamentals.constants.SharedConstants.GRASS;

import com.badlogic.gdx.Gdx;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.alejandria.showcase.stuff.modules.grids.Chunk;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Rectangle;

import java.util.List;

public class CrossChunkSelection extends Module<CrossChunkSelectionDrawable> {

    public CrossChunkSelection() {
        super(
            "Cross Chunk Selection",
            "Demonstrates how to select neighboring cells even from different chunks\n\n" +
                "Uses math for efficiency, each chunk has 4 squares\n\n" +
                "clicking the corner of a square selects neighbors\n\n" +
                "clicking near the center of a square makes it black"
        );
    }

    @Override
    public CrossChunkSelectionDrawable setup() {
        Gdx.gl.glLineWidth(3f);
        return new CrossChunkSelectionDrawable();
    }

    @Override
    public void touchDown(float x, float y) {
        if (!drawable.getBackground().contains(x, y)) {
            return;
        }
        Rectangle cell = find(x, y);
        cell.setColor(BLACK);
        selectNeighbors(x, y);
    }

    private Rectangle find(float x, float y) {
        x -= GRID_X;
        y -= GRID_Y;
        List<Chunk> chunks = drawable.getChunks();
        int column = (int) (x / CHUNK_SIZE);
        int row = (int) (y / CHUNK_SIZE);
        Chunk chunk = chunks.get(row + column * GRID_DIMENSION);
        List<Rectangle> cells = chunk.cells;
        column = (int) ((x % CHUNK_SIZE) / CELL_SIZE);
        row = (int) ((y % CHUNK_SIZE) / CELL_SIZE);
        return cells.get(row + column * CHUNK_DIMENSION);
    }

    private void selectNeighbors(float x, float y) {
        float margin = CELL_SIZE * 0.25f;
        float localX = x % CELL_SIZE, localY = y % CELL_SIZE;
        float difference = CELL_SIZE * 0.5f - margin;
        localX = Math.abs(localX - CELL_SIZE * 0.5f);
        localY = Math.abs(localY - CELL_SIZE * 0.5f);
        // Check corner
        if (localX >= difference && localY >= difference) {
            for (int column = -1; column < 2; column++) {
                for (int row = -1; row < 2; row++) {
                    float offsetX = x + column * margin;
                    float offsetY = y + row * margin;
                    if (offsetX < GRID_X || offsetX >= GRID_X + GRID_SIZE || offsetY < GRID_Y || offsetY >= GRID_Y + GRID_SIZE) {
                        continue;
                    }
                    Rectangle cell = find(offsetX, offsetY);
                    cell.setColor(GRASS);
                }
            }
        }
    }

    @Override
    protected void exit() {
        Gdx.gl.glLineWidth(1f);
    }
}