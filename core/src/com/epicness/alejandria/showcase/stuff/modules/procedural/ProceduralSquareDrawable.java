package com.epicness.alejandria.showcase.stuff.modules.procedural;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.GRID_DIMENSION_A;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.PIXEL_SIZE_A;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.SPACING_A;
import static com.epicness.fundamentals.SharedConstants.CENTER_X;
import static com.epicness.fundamentals.SharedConstants.CENTER_Y;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;

public class ProceduralSquareDrawable implements Drawable {

    private Color[][] pixels;
    private float pixelSize;
    private float spacingFactor;

    public ProceduralSquareDrawable() {
        setDimension(GRID_DIMENSION_A);
        setPixelSize(PIXEL_SIZE_A);
        setSpacingFactor(SPACING_A);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(Filled);
        float gridDimension = pixels.length;
        float gridSize = pixelSize * gridDimension + gridDimension * spacingFactor;
        for (int column = 0; column < gridDimension; column++) {
            Color[] pixelRow = pixels[column];
            for (int row = 0; row < gridDimension; row++) {
                Color pixel = pixelRow[row];
                shapeRenderer.setColor(pixel);
                float x = column * pixelSize + CENTER_X - gridSize / 2f + column * spacingFactor;
                float y = row * pixelSize + CENTER_Y - gridSize / 2f + row * spacingFactor;
                shapeRenderer.rect(x, y, pixelSize, pixelSize);
            }
        }
        shapeRenderer.end();
    }

    public Color[][] getPixels() {
        return pixels;
    }

    public int getDimension() {
        return pixels.length;
    }

    public void setDimension(int dimension) {
        pixels = new Color[dimension][dimension];
        for (int column = 0; column < dimension; column++) {
            for (int row = 0; row < dimension; row++) {
                pixels[column][row] = new Color();
            }
        }
    }

    public float getPixelSize() {
        return pixelSize;
    }

    public void setPixelSize(float pixelSize) {
        this.pixelSize = pixelSize;
    }

    public float getSpacingFactor() {
        return spacingFactor;
    }

    public void setSpacingFactor(float spacingFactor) {
        this.spacingFactor = spacingFactor;
    }
}