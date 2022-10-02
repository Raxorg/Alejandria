package com.epicness.alejandria.showcase.logic.input;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.modules.collisions.PixelPerfectCollision;

public class PixelPerfectCollisionInput extends ModuleInput<PixelPerfectCollision> {

    @Override
    protected Class<PixelPerfectCollision> setup() {
        return PixelPerfectCollision.class;
    }

    @Override
    public void touchDown(float x, float y) {
        x = MathUtils.map(150f, 150f + SHOWCASE_SIZE, 0f, CAMERA_WIDTH, x);
        y = MathUtils.map(150f, 150f + SHOWCASE_SIZE, 0f, CAMERA_HEIGHT, y);
        module.touchDown(x, y);
    }
}