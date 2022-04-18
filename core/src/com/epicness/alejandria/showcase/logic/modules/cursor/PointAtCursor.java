package com.epicness.alejandria.showcase.logic.modules.cursor;

import com.badlogic.gdx.Gdx;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.cursor.PointAtCursorDrawable;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.utils.AngleUtils;

public class PointAtCursor extends Module {

    private PointAtCursorDrawable drawable;

    @Override
    public void setup() {
        drawable = new PointAtCursorDrawable(sharedAssets.getTriangle());
        stuff.getShowcase().setDrawable(drawable);
    }

    @Override
    public void update(float delta) {
        float cursorX = Gdx.input.getX();
        float cursorY = Gdx.graphics.getHeight() - Gdx.input.getY();
        Sprited sprite = drawable.getTriangle();
        float spriteX = sprite.getX() + sprite.getOriginX();
        float spriteY = sprite.getY() + sprite.getOriginY();

        sprite.setRotation(AngleUtils.degreesBetweenPoints(cursorX, cursorY, spriteX, spriteY));
    }

    @Override
    public void exit() {
        drawable = null;
    }
}