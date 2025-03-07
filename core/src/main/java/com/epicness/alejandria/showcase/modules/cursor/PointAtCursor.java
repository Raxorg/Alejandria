package com.epicness.alejandria.showcase.modules.cursor;

import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.utils.AngleUtils;

public class PointAtCursor extends Module<PointAtCursorDrawable> {

    public PointAtCursor() {
        super("Point at Cursor", "Move your cursor");
    }

    @Override
    public PointAtCursorDrawable setup() {
        return new PointAtCursorDrawable(sharedAssets.getTriangle());
    }

    @Override
    public void mouseMoved(float cursorX, float cursorY) {
        // Triangle 1
        SpritePlus triangle = drawable.getTriangle1();
        float triangleX = triangle.getOriginBasedX();
        float triangleY = triangle.getOriginBasedY();
        float rotation = AngleUtils.degreesBetweenPoints(cursorX, cursorY, triangleX, triangleY);
        triangle.setRotation(rotation - 90f);
        // Triangle 2
        triangle = drawable.getTriangle2();
        triangleX = triangle.getOriginBasedX();
        triangleY = triangle.getOriginBasedY();
        rotation = AngleUtils.degreesBetweenPoints(cursorX, cursorY, triangleX, triangleY);
        triangle.setRotation(rotation - 90f);
    }
}