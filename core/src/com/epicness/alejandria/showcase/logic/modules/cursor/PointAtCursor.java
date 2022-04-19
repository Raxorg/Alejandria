package com.epicness.alejandria.showcase.logic.modules.cursor;

import com.epicness.alejandria.showcase.logic.input.PointAtCursorInput;
import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.cursor.PointAtCursorDrawable;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.utils.AngleUtils;

public class PointAtCursor extends Module {

    private PointAtCursorDrawable drawable;

    public PointAtCursor() {
        super("Point at Cursor");
    }

    @Override
    public Drawable setup() {
        ShowcaseInputHandler inputHandler = (ShowcaseInputHandler) logic.getHandler(ShowcaseInputHandler.class);
        inputHandler.setModuleInputHandler(new PointAtCursorInput());

        return drawable = new PointAtCursorDrawable(sharedAssets.getTriangle());
    }

    public void update(float cursorX, float cursorY) {
        Sprited sprite = drawable.getTriangle();
        float spriteX = sprite.getX() + sprite.getOriginX();
        float spriteY = sprite.getY() + sprite.getOriginY();

        sprite.setRotation(AngleUtils.degreesBetweenPoints(cursorX, cursorY, spriteX, spriteY) - 90f);
    }

    @Override
    public void exit() {
        drawable = null;
        ShowcaseInputHandler inputHandler = (ShowcaseInputHandler) logic.getHandler(ShowcaseInputHandler.class);
        inputHandler.setModuleInputHandler(null);
    }
}