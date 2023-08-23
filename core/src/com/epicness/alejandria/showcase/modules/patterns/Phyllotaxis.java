package com.epicness.alejandria.showcase.modules.patterns;

import static com.epicness.alejandria.showcase.constants.PatternsConstants.PHYLLOTAXIS_CIRCLES;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;

public class Phyllotaxis extends Module<PhyllotaxisModuleDrawable> {

    private float speed;
    private boolean accelerating;
    private float[] angles;

    public Phyllotaxis() {
        super("Phyllotaxis", "Hold to accelerate");
    }

    @Override
    protected PhyllotaxisModuleDrawable setup() {
        speed = 15f;
        accelerating = false;

        drawable = new PhyllotaxisModuleDrawable();
        Circle[] circles = drawable.getCircles();
        angles = new float[PHYLLOTAXIS_CIRCLES];
        float a = 137.5f;
        float c = 12f;
        float radius, x, y;
        for (int i = 0; i < PHYLLOTAXIS_CIRCLES; i++) {
            angles[i] = a * i;
            radius = (float) (c * Math.sqrt(i));
            x = radius * MathUtils.cosDeg(angles[i]) + CAMERA_HALF_WIDTH;
            y = radius * MathUtils.sinDeg(angles[i]) + CAMERA_HALF_HEIGHT;
            Color color = Color.WHITE.cpy().fromHsv(angles[i] % 360, 1, 1);
            circles[i] = new Circle(x, y, 9f, color);
        }
        return drawable;
    }

    @Override
    public void update(float delta) {
        if (accelerating) {
            speed += 180f * delta;
        }
        Circle[] circles = drawable.getCircles();
        for (int i = 0; i < PHYLLOTAXIS_CIRCLES; i++) {
            angles[i] += delta * speed;
            Color color = Color.WHITE.cpy().fromHsv(angles[i] % 360, 1, 1);
            circles[i].setColor(color);
        }
    }

    @Override
    public void touchDown(float x, float y) {
        accelerating = true;
    }

    @Override
    public void touchUp(float x, float y) {
        accelerating = false;
    }
}