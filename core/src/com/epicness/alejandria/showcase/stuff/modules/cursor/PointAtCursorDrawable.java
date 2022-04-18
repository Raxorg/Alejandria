package com.epicness.alejandria.showcase.stuff.modules.cursor;

import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class PointAtCursorDrawable implements Drawable {

    private final Sprited triangle;

    public PointAtCursorDrawable(Sprite cursorSprite) {
        triangle = new Sprited(cursorSprite);
        triangle.setOriginCenter();
        triangle.setOriginBasedPosition(INITIAL_WINDOW_SIZE / 2f, INITIAL_WINDOW_SIZE / 2f);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        ScreenUtils.clear(Color.NAVY);
        spriteBatch.begin();
        triangle.draw(spriteBatch);
        spriteBatch.end();
    }

    public Sprited getTriangle() {
        return triangle;
    }
}