package com.epicness.alejandria.showcase.logic.modules.procedural;

import static com.badlogic.gdx.Input.Keys.NUM_7;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.GRID_SIZE;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.PIXEL_SIZE_A;
import static com.epicness.alejandria.showcase.constants.ProceduralSquareConstants.PIXEL_SIZE_B;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.procedural.ProceduralSquareDrawable;
import com.epicness.alejandria.showcase.stuff.modules.procedural.helpers.Pixel;

import java.util.ArrayList;
import java.util.List;

public class ProceduralSquare extends Module {

    private ProceduralSquareDrawable drawable;

    public ProceduralSquare() {
        super("Procedural Square");
    }

    @Override
    public Drawable setup() {
        drawable = new ProceduralSquareDrawable();

        List<List<Pixel>> pixels = drawable.getPixels();
        for (int column = 0; column < GRID_SIZE; column++) {
            pixels.add(new ArrayList<>());
            for (int row = 0; row < GRID_SIZE; row++) {
                float lerpValue = tunnel(column, row, GRID_SIZE, 1f);
                Pixel pixel = new Pixel(Color.WHITE.cpy().lerp(Color.BLACK, lerpValue));
                pixel.setSize(PIXEL_SIZE_A);
                pixels.get(column).add(pixel);
            }
        }
        return drawable;
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
            for (int i = 0; i < drawable.getPixels().size(); i++) {
                for (int j = 0; j < drawable.getPixels().get(i).size(); j++) {
                    drawable.getPixels().get(i).get(j).setSize(PIXEL_SIZE_B);
                }
            }
        }
    }
}