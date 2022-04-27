package com.epicness.alejandria.showcase.stuff.modules.procedural;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.GRID_SIZE;
import static com.epicness.fundamentals.SharedConstants.CENTER_X;
import static com.epicness.fundamentals.SharedConstants.CENTER_Y;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.procedural.helpers.Pixel;

import java.util.ArrayList;
import java.util.List;

public class ProceduralSquareDrawable implements Drawable {

    private final List<List<Pixel>> pixels;

    public ProceduralSquareDrawable() {
        pixels = new ArrayList<>();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(Filled);
        float pixelSize = pixels.get(0).get(0).getSize();
        float gridSize = pixelSize * GRID_SIZE;
        for (int column = 0; column < pixels.size(); column++) {
            List<Pixel> squareRow = pixels.get(column);
            for (int row = 0; row < squareRow.size(); row++) {
                Pixel pixel = squareRow.get(row);
                shapeRenderer.setColor(pixel.getColor());
                float x = column * pixelSize + CENTER_X - gridSize / 2f;
                float y = row * pixelSize + CENTER_Y - gridSize / 2f;
                shapeRenderer.rect(x, y, pixelSize, pixelSize);
            }
        }
        shapeRenderer.end();
    }

    public List<List<Pixel>> getPixels() {
        return pixels;
    }
}