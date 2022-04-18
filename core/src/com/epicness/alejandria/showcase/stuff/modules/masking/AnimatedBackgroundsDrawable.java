package com.epicness.alejandria.showcase.stuff.modules.masking;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.AnimatedBackground;

public class AnimatedBackgroundsDrawable implements Drawable {

    private final AnimatedBackground background3, background2, background1;

    public AnimatedBackgroundsDrawable(Sprite pixel, Sprite weirdShape, OrthographicCamera camera) {
        background1 = new AnimatedBackground(
                50f, 50f,
                700f, 700f,
                Color.BLUE,
                weirdShape,
                pixel,
                camera,
                15,
                15,
                15f);
        background2 = new AnimatedBackground(
                300f, 300f,
                300f, 200f,
                Color.GREEN,
                pixel,
                pixel,
                camera,
                5,
                5,
                25f);
        background3 = new AnimatedBackground(
                100f, 100f,
                400f, 300f,
                Color.RED,
                pixel,
                weirdShape,
                camera,
                10,
                10,
                50f);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        background1.draw(spriteBatch);
        background2.draw(spriteBatch);
        background3.draw(spriteBatch);
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