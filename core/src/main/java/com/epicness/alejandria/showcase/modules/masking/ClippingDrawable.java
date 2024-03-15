package com.epicness.alejandria.showcase.modules.masking;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_HALF_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_Y;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.AnimatedBackground;

public class ClippingDrawable implements ModuleDrawable {

    private final AnimatedBackground background1, background2, background3;

    public ClippingDrawable(Sprite pixel, Sprite weirdShape, OrthographicCamera camera) {
        background1 = new AnimatedBackground(
            100f, SHOWCASE_Y,
            SHOWCASE_SIZE, SHOWCASE_SIZE,
            Color.BLUE.cpy(),
            weirdShape,
            pixel,
            camera,
            15,
            15,
            15f);
        background2 = new AnimatedBackground(
            200f, CAMERA_HALF_HEIGHT,
            SHOWCASE_SIZE - 200f, 300f,
            Color.GREEN.cpy(),
            pixel,
            pixel,
            camera,
            8,
            5,
            25f);
        background3 = new AnimatedBackground(
            200f, 200f,
            SHOWCASE_HALF_SIZE, SHOWCASE_HALF_SIZE,
            Color.RED.cpy(),
            pixel,
            pixel,
            camera,
            7,
            7,
            50f);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        background1.draw(spriteBatch);
        background2.draw(spriteBatch);
        background3.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
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