package com.epicness.alejandria.showcase.stuff.modules.grids;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;

import java.util.ArrayList;
import java.util.List;

public class CrossChunkSelectionDrawable implements Drawable {

    private final List<Chunk> chunks;

    public CrossChunkSelectionDrawable() {
        chunks = new ArrayList<>();
        int dimension = 5;
        float w = CAMERA_WIDTH / dimension, h = CAMERA_HEIGHT / dimension;
        for (int column = 0; column < 5; column++) {
            for (int row = 0; row < 5; row++) {
                chunks.add(new Chunk(column * w, row * h, w, h, 0, 3));
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(Filled);
        for (int i = 0; i < chunks.size(); i++) {
            chunks.get(i).draw(shapeRenderer);
        }
        shapeRenderer.end();
    }
}