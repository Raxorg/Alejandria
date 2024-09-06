package com.epicness.alejandria.showcase.modules.patterns;

import static com.badlogic.gdx.Input.Keys.F;
import static com.badlogic.gdx.Input.Keys.SPACE;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.math.MathUtils.PI2;
import static com.epicness.alejandria.showcase.constants.PatternsConstants.CENTERED_CENTER_DISTANCE;
import static com.epicness.alejandria.showcase.constants.PatternsConstants.CENTERED_OWN_CENTER_DISTANCE;
import static com.epicness.alejandria.showcase.constants.PatternsConstants.UNCENTERED_CENTER_DISTANCE;
import static com.epicness.alejandria.showcase.constants.PatternsConstants.UNCENTERED_OWN_CENTER_DISTANCE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.BASIC_COLORS;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;

public class Spiral extends Module<SpiralDrawable> {

    private Sprite[] dots;
    private Sprite dot;
    @SuppressWarnings("FieldCanBeLocal")
    private float centerX, centerY, x, y;
    private float time;
    private int colorMode;
    private float centerDistance, ownCenterDistance;
    private boolean centered;

    public Spiral() {
        super(
            "Spiral",
            "Press space to change between white, basic colors and random colors\n\n" +
                "Press F to change the distance of the dots from the center"
        );
    }

    @Override
    protected SpiralDrawable setup() {
        drawable = new SpiralDrawable(sharedAssets.getDot());
        dots = drawable.getDots();
        dot = null;
        time = 0f;
        colorMode = 1;
        centered = true;
        centerDistance = CENTERED_CENTER_DISTANCE;
        ownCenterDistance = CENTERED_OWN_CENTER_DISTANCE;
        changeColorMode();
        return drawable;
    }

    @Override
    public void update(float delta) {
        time += delta;
        for (int i = 0; i < dots.length; i++) {
            dot = dots[i];

            centerX = VIEWPORT_HALF_WIDTH + centerDistance * MathUtils.cos(PI2 * i / dots.length);
            centerY = VIEWPORT_HALF_HEIGHT + centerDistance * MathUtils.sin(PI2 * i / dots.length);

            x = centerX + ownCenterDistance * MathUtils.cos(MathUtils.PI2 * (time * i / dots.length));
            y = centerY + ownCenterDistance * MathUtils.sin(MathUtils.PI2 * (time * i / dots.length));

            dot.setOriginBasedPosition(x, y);
        }
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode) {
            case SPACE:
                changeColorMode();
                break;
            case F:
                centered = !centered;
                centerDistance = centered ? CENTERED_CENTER_DISTANCE : UNCENTERED_CENTER_DISTANCE;
                ownCenterDistance = centered ? CENTERED_OWN_CENTER_DISTANCE : UNCENTERED_OWN_CENTER_DISTANCE;
        }
    }

    private void changeColorMode() {
        switch (colorMode) {
            case 1:
                for (int i = 0; i < dots.length; i++) {
                    dots[i].setColor(BASIC_COLORS[i % 5]);
                }
                colorMode = 2;
                break;
            case 2:
                for (int i = 0; i < dots.length; i++) {
                    dots[i].setColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1f);
                }
                colorMode = 3;
                break;
            case 3:
                for (int i = 0; i < dots.length; i++) {
                    dots[i].setColor(WHITE);
                }
                colorMode = 1;
        }
    }
}