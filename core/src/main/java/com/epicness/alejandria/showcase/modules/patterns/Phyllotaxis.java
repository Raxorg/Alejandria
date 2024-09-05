package com.epicness.alejandria.showcase.modules.patterns;

import static com.epicness.alejandria.showcase.constants.PatternsConstants.PHYLLOTAXIS_BALL_RADIUS;
import static com.epicness.alejandria.showcase.constants.PatternsConstants.PHYLLOTAXIS_CIRCLES;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;

public class Phyllotaxis extends Module<PhyllotaxisDrawable> {

    private float speed;
    private boolean accelerating;
    private float[] angles;

    public Phyllotaxis() {
        super(
            "Phyllotaxis",
            "Phyllotaxis pattern colored using the HSV model instead of RGB\n\n" +
                "Hold to accelerate\n\n" +
                "Try blinking when it goes fast :o"
        );
    }

    @Override
    protected PhyllotaxisDrawable setup() {
        speed = 15f;
        accelerating = false;

        drawable = new PhyllotaxisDrawable();
        Circle[] circles = drawable.getCircles();
        angles = new float[PHYLLOTAXIS_CIRCLES];
        float a = 137.5f;
        float c = 12f;
        float radius, x, y;
        for (int i = 0; i < PHYLLOTAXIS_CIRCLES; i++) {
            angles[i] = a * i;
            radius = (float) (c * Math.sqrt(i));
            x = VIEWPORT_HALF_WIDTH - PHYLLOTAXIS_BALL_RADIUS + radius * MathUtils.cosDeg(angles[i]);
            y = VIEWPORT_HALF_HEIGHT - PHYLLOTAXIS_BALL_RADIUS + radius * MathUtils.sinDeg(angles[i]);
            Color color = new Color().fromHsv(angles[i] % 360, 1, 1);
            color.a = 1f;
            circles[i] = new Circle(x, y, PHYLLOTAXIS_BALL_RADIUS, color);
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
            Color color = new Color().fromHsv(angles[i] % 360, 1, 1);
            color.a = 1f;
            circles[i].setColor(color);
        }
    }

    @Override
    public void touchDown(float x, float y, int button) {
        accelerating = true;
    }

    @Override
    public void touchUp(float x, float y) {
        accelerating = false;
    }
}