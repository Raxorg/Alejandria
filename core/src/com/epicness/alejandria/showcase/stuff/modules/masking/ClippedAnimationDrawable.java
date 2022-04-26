package com.epicness.alejandria.showcase.stuff.modules.masking;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.AnimatedBackground;

public class ClippedAnimationDrawable implements Drawable {

    private final AnimatedBackground background1, background2, background3;

    public ClippedAnimationDrawable(Sprite pixel, Sprite weirdShape, OrthographicCamera camera) {
        background1 = new AnimatedBackground(
                50f, 50f,
                CAMERA_WIDTH - 100f, CAMERA_HEIGHT - 100f,
                Color.BLUE,
                weirdShape,
                pixel,
                camera,
                15,
                15,
                15f);
        background2 = new AnimatedBackground(
                100f, 500f,
                CAMERA_WIDTH - 200f, 400f,
                Color.GREEN,
                pixel,
                pixel,
                camera,
                8,
                5,
                25f);
        background3 = new AnimatedBackground(
                100f, 100f,
                500f, 500f,
                Color.RED,
                pixel,
                weirdShape,
                camera,
                7,
                7,
                50f);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        background1.draw(spriteBatch);
        background2.draw(spriteBatch);
        background3.draw(spriteBatch);
        spriteBatch.end();
    }

    public AnimatedBackground getBackground1() {
        return background1;
    }

    public AnimatedBackground getBackground2() {
        return background2;
    }

    public AnimatedBackground getBackground3() {
        return background3;
    }
}