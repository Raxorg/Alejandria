package com.epicness.alejandria.module.stuff.modules.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.module.stuff.modules.Module;

import java.util.ArrayList;

import static com.badlogic.gdx.Input.Keys.NUM_7;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.alejandria.ModuleID.PROCEDURAL_SQUARE;
import static com.epicness.fundamentals.SharedConstants.DIRT;
import static com.epicness.fundamentals.SharedConstants.GRASS;

public class ProceduralSquare extends Module {

    private ArrayList<ArrayList<Pixel>> pixels;
    private float pixelSize = 15f;

    public ProceduralSquare() {
        super(PROCEDURAL_SQUARE);
    }

    @Override
    public void setup() {
        pixels = new ArrayList<>();
        int size = 30;
        for (int column = 0; column < size; column++) {
            pixels.add(new ArrayList<>());
            for (int row = 0; row < size; row++) {
                float lerpValue = tunnel(column, row, size, 1f);
                Pixel pixel = new Pixel(Color.WHITE.cpy().lerp(Color.BLACK, lerpValue));
                pixels.get(column).add(pixel);
            }
        }
    }

    private float tunnel(int column, int row, float size, float factor) {
        float center = size / 2f;

        float leftXGradient = (center / factor - column) * (2f * factor);
        float rightXGradient = (center / factor - (size - column) + 1f) * (2f * factor);
        float xGradient = Math.max(leftXGradient, rightXGradient);

        float upYGradient = (center / factor - row) * (2f * factor);
        float downYGradient = (center / factor - (size - row) + 1f) * (2f * factor);
        float yGradient = Math.max(upYGradient, downYGradient);

        float lerpValue = Math.max(xGradient, yGradient);

        return MathUtils.map(0f, size, 0f, 1f, lerpValue);
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(NUM_7)) {
            pixelSize = 10f;
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        ScreenUtils.clear(DIRT);
        shapeRenderer.begin(Filled);
        for (int x = 0; x < pixels.size(); x++) {
            ArrayList<Pixel> squareRow = pixels.get(x);
            for (int y = 0; y < pixels.get(x).size(); y++) {
                Pixel square = squareRow.get(y);
                shapeRenderer.setColor(square.color);
                shapeRenderer.rect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
                shapeRenderer.setColor(GRASS);
                shapeRenderer.rect(x * pixelSize + pixels.size() * pixelSize, y * pixelSize, pixelSize, pixelSize);
                shapeRenderer.setColor(Color.BLACK);
                shapeRenderer.rect(x * pixelSize, y * pixelSize + pixels.size() * pixelSize, pixelSize, pixelSize);
            }
        }
        shapeRenderer.end();
    }

    public static class Pixel {
        private final Color color;

        public Pixel(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }
}