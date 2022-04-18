package com.epicness.alejandria.showcase.stuff.modules.procedural;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.fundamentals.SharedConstants.DIRT;
import static com.epicness.fundamentals.SharedConstants.GRASS;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
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
        ScreenUtils.clear(DIRT);
        shapeRenderer.begin(Filled);
        for (int x = 0; x < pixels.size(); x++) {
            List<Pixel> squareRow = pixels.get(x);
            for (int y = 0; y < pixels.get(x).size(); y++) {
                Pixel pixel = squareRow.get(y);
                shapeRenderer.setColor(pixel.getColor());
                float size = pixel.getSize();
                shapeRenderer.rect(x * size, y * size, size, size);
                shapeRenderer.setColor(GRASS);
                shapeRenderer.rect(x * size + pixels.size() * size, y * size, size, size);
                shapeRenderer.setColor(Color.BLACK);
                shapeRenderer.rect(x * size, y * size + pixels.size() * size, size, size);
            }
        }
        shapeRenderer.end();
    }

    public List<List<Pixel>> getPixels() {
        return pixels;
    }
}