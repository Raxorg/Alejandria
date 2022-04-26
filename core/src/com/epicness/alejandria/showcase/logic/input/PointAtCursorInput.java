package com.epicness.alejandria.showcase.logic.input;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.modules.cursor.PointAtCursor;

public class PointAtCursorInput extends ModuleInput {

    @Override
    public void mouseMoved(float x, float y) {
        x = MathUtils.map(150f, 150f + SHOWCASE_SIZE, 0f, CAMERA_WIDTH, x);
        y = MathUtils.map(150f, 150f + SHOWCASE_SIZE, 0f, CAMERA_HEIGHT, y);
        PointAtCursor pointAtCursor = (PointAtCursor) logic.getHandler(PointAtCursor.class);
        pointAtCursor.mouseMoved(x, y);
    }
}