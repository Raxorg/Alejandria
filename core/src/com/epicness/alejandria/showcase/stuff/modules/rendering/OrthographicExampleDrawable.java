package com.epicness.alejandria.showcase.stuff.modules.rendering;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class OrthographicExampleDrawable implements Drawable {

    private final Sprited weirdShape;

    public OrthographicExampleDrawable(Sprite weirdShapeSprite) {
        weirdShape = new Sprited(weirdShapeSprite);
        weirdShape.setSize(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }
}