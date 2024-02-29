package com.epicness.alejandria.showcase.modules.procedural;

import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.GRID_DIMENSION_A;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.PIXEL_SIZE_A;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.SPACING_A;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class ProceduralSquareDrawable implements ModuleDrawable {

    private Color[][] pixels;
    private float pixelSize;
    private float spacingFactor;

    public ProceduralSquareDrawable() {
        setDimension(GRID_DIMENSION_A);
        setPixelSize(PIXEL_SIZE_A);
        setSpacingFactor(SPACING_A);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        float gridDimension = pixels.length;
        float gridSize = pixelSize * gridDimension + gridDimension * spacingFactor;
        for (int column = 0; column < gridDimension; column++) {
            for (int row = 0; row < gridDimension; row++) {
                float x = column * pixelSize + CAMERA_HALF_WIDTH - gridSize / 2f + column * spacingFactor;
                float y = row * pixelSize + CAMERA_HALF_HEIGHT - gridSize / 2f + row * spacingFactor;
                shapeDrawer.filledRectangle(x, y, pixelSize, pixelSize, pixels[column][row]);
            }
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
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