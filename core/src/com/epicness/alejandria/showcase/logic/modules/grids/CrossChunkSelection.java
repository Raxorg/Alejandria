package com.epicness.alejandria.showcase.logic.modules.grids;

import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CELL_SIZE;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CHUNK_DIMENSION;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.CHUNK_SIZE;
import static com.epicness.alejandria.showcase.constants.CrossChunkSelectionConstants.DIMENSION;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.GRASS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.grids.Chunk;
import com.epicness.alejandria.showcase.stuff.modules.grids.CrossChunkSelectionDrawable;
import com.epicness.alejandria.showcase.stuff.modules.grids.RectangleCell;

import java.util.List;

public class CrossChunkSelection extends Module<CrossChunkSelectionDrawable> {

    public CrossChunkSelection() {
        super(
                "Cross Chunk Selection",
                "This demonstrates how to select neighboring cells even from different chunks"
        );
    }

    @Override
    public CrossChunkSelectionDrawable setup() {
        Gdx.gl.glLineWidth(3f);
        return new CrossChunkSelectionDrawable();
    }

    @Override
    public void touchDown(float x, float y) {
        if (x < 0 || x >= CAMERA_WIDTH || y < 0 || y >= CAMERA_HEIGHT) {
            return;
        }
        RectangleCell cell = find(x, y);
        cell.color = Color.BLACK;
        selectNeighbors(x, y);
    }

    private RectangleCell find(float x, float y) {
        List<Chunk> chunks = drawable.getChunks();
        int column = (int) (x / CHUNK_SIZE);
        int row = (int) (y / CHUNK_SIZE);
        Chunk chunk = chunks.get(row + column * DIMENSION);
        List<RectangleCell> cells = chunk.cells;
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
                    if (offsetX < 0 || offsetX >= CAMERA_WIDTH || offsetY < 0 || offsetY >= CAMERA_HEIGHT) {
                        continue;
                    }
                    RectangleCell cell = find(offsetX, offsetY);
                    cell.color = GRASS;
                }
            }
        }
    }

    @Override
    protected void exit() {
        Gdx.gl.glLineWidth(1f);
    }
}