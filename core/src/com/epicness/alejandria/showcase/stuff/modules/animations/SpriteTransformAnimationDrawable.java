package com.epicness.alejandria.showcase.stuff.modules.animations;

import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class SpriteTransformAnimationDrawable implements Drawable {

    private final Sprited sprited1, sprited2;

    public SpriteTransformAnimationDrawable(Sprite sprite1, Sprite sprite2) {
        sprited1 = new Sprited(sprite1);
        float x1 = INITIAL_WINDOW_SIZE / 3f;
        float y1 = INITIAL_WINDOW_SIZE / 3f;
        sprited1.setOriginCenter();
        sprited1.setOriginBasedPosition(x1, y1);

        sprited2 = new Sprited(sprite2);
        sprited2.setPosition(x1 - sprite1.getWidth() * 1.5f, y1 - sprite1.getHeight() * 1.5f);
        sprited2.setOrigin(x1, y1);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        sprited1.draw(spriteBatch);
        sprited2.draw(spriteBatch);
        spriteBatch.end();
    }

    public Sprited getSprited1() {
        return sprited1;
    }

    public Sprited getSprited2() {
        return sprited2;
    }
}