package com.epicness.alejandria.showcase.stuff.modules.animations;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.CENTER_X;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.CENTER_Y;
import static com.epicness.alejandria.showcase.constants.SpriteRotationAnimationConstants.SPRITED_1_SIZE;
import static com.epicness.alejandria.showcase.constants.SpriteRotationAnimationConstants.SPRITED_2_SIZE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class SpriteRotationAnimationDrawable implements Drawable {

    private final Sprited sprited1, sprited2;

    public SpriteRotationAnimationDrawable(Sprite sprite1, Sprite sprite2) {
        sprited1 = new Sprited(sprite1);
        sprited1.setSize(SPRITED_1_SIZE);
        sprited1.setOriginCenter();
        sprited1.setOriginBasedPosition(CENTER_X, CENTER_Y);

        sprited2 = new Sprited(sprite2);
        sprited2.setSize(SPRITED_2_SIZE);
        sprited2.setX(CENTER_X - SPRITED_2_SIZE - SPRITED_1_SIZE);
        sprited2.setY(CENTER_Y - SPRITED_2_SIZE - SPRITED_1_SIZE);
        sprited2.setOrigin(SPRITED_2_SIZE + SPRITED_1_SIZE, SPRITED_2_SIZE + SPRITED_1_SIZE);
        sprited2.setColor(Color.ORANGE);
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