package com.epicness.alejandria.showcase.logic.input;

import com.epicness.alejandria.showcase.logic.modules.cursor.PointAtCursor;

public class PointAtCursorInput extends ModuleInput {

    @Override
    public void mouseMoved(float x, float y) {
        PointAtCursor pointAtCursor = (PointAtCursor) logic.getHandler(PointAtCursor.class);
        pointAtCursor.mouseMoved(x, y);
    }
}