package com.epicness.alejandria.showcase.stuff.modules.shaders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class VignetteShaderDrawable implements Drawable {

    private final Sprited sprited1, sprited2, sprited3;

    public VignetteShaderDrawable(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        sprited1 = new Sprited(sprite1);
        sprited2 = new Sprited(sprite2);
        sprited3 = new Sprited(sprite3);

        sprited1.setSize(400f, 400f);
        sprited1.setColor(Color.ORANGE);

        sprited2.setPosition(400f, 400f);
        sprited2.setSize(400f, 400f);
        sprited2.setColor(Color.PURPLE);

        sprited3.setPosition(0f, 400f);
        sprited3.setSize(400f, 400f);
        sprited3.setColor(Color.CHARTREUSE);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        ScreenUtils.clear(Color.RED);
        spriteBatch.begin();
        sprited1.draw(spriteBatch);
        sprited2.draw(spriteBatch);
        sprited3.draw(spriteBatch);
        spriteBatch.end();
    }
}