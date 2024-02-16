package com.epicness.alejandria.showcase.modules.procedural;

import static com.badlogic.gdx.Input.Keys.NUM_1;
import static com.badlogic.gdx.Input.Keys.NUM_2;
import static com.badlogic.gdx.Input.Keys.SPACE;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.GRID_DIMENSION_A;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.GRID_DIMENSION_B;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.PIXEL_SIZE_A;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.PIXEL_SIZE_B;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.SPACING_A;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.SPACING_B;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;

public class ProceduralSquare extends Module<ProceduralSquareDrawable> {

    private float time;

    public ProceduralSquare() {
        super(
            "Procedural Square",
            "1 to change the pixel size\n\n2 to change grid size\n\nSpace to toggle pixel spacing"
        );
    }

    @Override
    public ProceduralSquareDrawable setup() {
        return new ProceduralSquareDrawable();
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
        if (Gdx.input.isKeyJustPressed(SPACE)) {
            drawable.setSpacingFactor(drawable.getSpacingFactor() == SPACING_A ? SPACING_B : SPACING_A);
        }
        if (Gdx.input.isKeyJustPressed(NUM_1)) {
            drawable.setPixelSize(drawable.getPixelSize() == PIXEL_SIZE_A ? PIXEL_SIZE_B : PIXEL_SIZE_A);
        }
        if (Gdx.input.isKeyJustPressed(NUM_2)) {
            drawable.setDimension(drawable.getDimension() == GRID_DIMENSION_A ? GRID_DIMENSION_B : GRID_DIMENSION_A);
        }
        time += delta;
        float t = time % 10;
        Color[][] pixels = drawable.getPixels();
        float gridDimension = pixels.length;
        for (int column = 0; column < gridDimension; column++) {
            for (int row = 0; row < gridDimension; row++) {
                Color pixel = pixels[column][row];
                float lerpValue = 1 - t / 10f * (1f - tunnel(column, row, gridDimension, 1f));
                lerpValue = Interpolation.exp10In.apply(lerpValue);
                Color color = Color.WHITE.cpy().lerp(Color.BLACK, lerpValue);
                pixel.set(color);
            }
        }
    }
}