package com.epicness.alejandria.showcase.modules.cursor;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.interfaces.Drawable;

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
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        spriteBatch.begin();
        triangle1.draw(spriteBatch);
        triangle2.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
        shapeBatch.begin();
        shapeBatch.rect(triangle1.getBoundingRectangle());
        shapeBatch.rect(triangle2.getBoundingRectangle());
        shapeBatch.end();
    }

    public Sprited getTriangle1() {
        return triangle1;
    }

    public Sprited getTriangle2() {
        return triangle2;
    }
}