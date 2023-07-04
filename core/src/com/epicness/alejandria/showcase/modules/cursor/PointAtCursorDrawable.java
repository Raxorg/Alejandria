package com.epicness.alejandria.showcase.modules.cursor;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class PointAtCursorDrawable implements Drawable {

    private final Sprited triangle1, triangle2;

    public PointAtCursorDrawable(Sprite triangleSprite) {
        triangle1 = new Sprited(triangleSprite);
        triangle1.setOriginCenter();
        triangle1.setOriginBasedPosition(CAMERA_HALF_WIDTH + 200f, CAMERA_HALF_HEIGHT);

        triangle2 = new Sprited(triangleSprite);
        triangle2.setOriginCenter();
        triangle2.setOriginBasedPosition(CAMERA_HALF_WIDTH - 200f, CAMERA_HALF_HEIGHT);
        triangle2.useBilinearFilter();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        triangle1.draw(spriteBatch);
        triangle2.draw(spriteBatch);
        spriteBatch.end();
    }

    public Sprited getTriangle1() {
        return triangle1;
    }

    public Sprited getTriangle2() {
        return triangle2;
    }
}