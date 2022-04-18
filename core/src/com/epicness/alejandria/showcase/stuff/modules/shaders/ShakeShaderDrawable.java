package com.epicness.alejandria.showcase.stuff.modules.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class ShakeShaderDrawable implements Drawable {

    private final Sprited weirdShape;

    public ShakeShaderDrawable(Sprite weirdShapeSprite) {
        weirdShape = new Sprited(weirdShapeSprite);
        float x = Gdx.graphics.getWidth() / 2f - weirdShape.getWidth() / 2f;
        float y = Gdx.graphics.getHeight() / 2f - weirdShape.getHeight() / 2f;
        weirdShape.setPosition(x, y);
        weirdShape.setColor(Color.ORANGE);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }
}