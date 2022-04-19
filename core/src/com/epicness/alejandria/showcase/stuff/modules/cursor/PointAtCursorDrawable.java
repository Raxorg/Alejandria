package com.epicness.alejandria.showcase.stuff.modules.cursor;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class PointAtCursorDrawable implements Drawable {

    private final Sprited triangle;

    public PointAtCursorDrawable(Sprite cursorSprite) {
        triangle = new Sprited(cursorSprite);
        triangle.setOriginCenter();
        triangle.setOriginBasedPosition(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        triangle.draw(spriteBatch);
        spriteBatch.end();
    }

    public Sprited getTriangle() {
        return triangle;
    }
}