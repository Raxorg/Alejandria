package com.epicness.alejandria.showcase.stuff.modules.shaders;

import static com.epicness.fundamentals.SharedConstants.CENTER_X;
import static com.epicness.fundamentals.SharedConstants.CENTER_Y;
import static com.epicness.fundamentals.SharedConstants.HALF_CAMERA_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class VignetteShaderDrawable implements Drawable {

    private final Sprited square1, square2, square3, square4;

    public VignetteShaderDrawable(Sprite squareSprite) {
        square1 = new Sprited(squareSprite);
        square2 = new Sprited(squareSprite);
        square3 = new Sprited(squareSprite);
        square4 = new Sprited(squareSprite);

        square1.setSize(HALF_CAMERA_WIDTH);
        square1.setColor(Color.ORANGE);

        square2.setPosition(CENTER_X, CENTER_Y);
        square2.setSize(HALF_CAMERA_WIDTH);
        square2.setColor(Color.PURPLE);

        square3.setPosition(0f, CENTER_Y);
        square3.setSize(HALF_CAMERA_WIDTH);
        square3.setColor(Color.CHARTREUSE);

        square4.setPosition(CENTER_X, 0f);
        square4.setSize(HALF_CAMERA_WIDTH);
        square4.setColor(Color.CYAN);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        square1.draw(spriteBatch);
        square2.draw(spriteBatch);
        square3.draw(spriteBatch);
        square4.draw(spriteBatch);
        spriteBatch.end();
    }
}