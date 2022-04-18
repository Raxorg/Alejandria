package com.epicness.alejandria.showcase.stuff.modules.shaders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class InvertShaderDrawable implements Drawable {

    private final Sprited weirdShape;

    public InvertShaderDrawable(Sprite weirdShapeSprite) {
        weirdShape = new Sprited(weirdShapeSprite);
        weirdShape.setColor(Color.RED);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }
}
