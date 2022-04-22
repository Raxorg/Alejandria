package com.epicness.alejandria.showcase.stuff.modules.cursor;

import static com.epicness.fundamentals.SharedConstants.CENTER_X;
import static com.epicness.fundamentals.SharedConstants.CENTER_Y;

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
        triangle1.setOriginBasedPosition(CENTER_X + 200f, CENTER_Y);

        triangle2 = new Sprited(triangleSprite);
        triangle2.setOriginCenter();
        triangle2.setOriginBasedPosition(CENTER_X - 200f, CENTER_Y);
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