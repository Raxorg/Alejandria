package com.epicness.alejandria.showcase.stuff;

import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.fundamentals.stuff.SpritedText;

public class Showcase {

    private Drawable drawable;
    private final SpritedText topStripe;

    public Showcase(Sprite pixel, BitmapFont font) {
        topStripe = new SpritedText(pixel, font);
        topStripe.setSize(CAMERA_WIDTH, 100f);
    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        if (drawable != null) {
            drawable.draw(spriteBatch, shapeRenderer);
        }
        spriteBatch.begin();
        topStripe.draw(spriteBatch);
        spriteBatch.end();
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setText(String text) {
        topStripe.setText(text);
    }
}