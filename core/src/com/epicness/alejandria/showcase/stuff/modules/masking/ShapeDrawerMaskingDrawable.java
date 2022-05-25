package com.epicness.alejandria.showcase.stuff.modules.masking;

import static com.epicness.fundamentals.SharedConstants.CENTER_X;
import static com.epicness.fundamentals.SharedConstants.CENTER_Y;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.masking.helpers.SDTriangle;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class ShapeDrawerMaskingDrawable implements Drawable {

    private final ShapeDrawer shapeDrawer;
    private final float size;
    private final SDTriangle triangle1, triangle2, triangle3, triangle4;

    public ShapeDrawerMaskingDrawable(SpriteBatch spriteBatch, Sprite pixel) {
        shapeDrawer = new ShapeDrawer(spriteBatch);
        shapeDrawer.setTextureRegion(pixel);

        size = 200f;

        triangle1 = new SDTriangle(
                CENTER_X - size, CENTER_Y - size,
                CENTER_X, CENTER_Y,
                CENTER_X + size, CENTER_Y - size);
        triangle1.setColor(Color.BLUE);

        triangle2 = new SDTriangle(
                CENTER_X + size, CENTER_Y + size,
                CENTER_X, CENTER_Y,
                CENTER_X + size, CENTER_Y - size);
        triangle2.setColor(Color.GREEN);

        triangle3 = new SDTriangle(
                CENTER_X - size, CENTER_Y + size,
                CENTER_X, CENTER_Y,
                CENTER_X + size, CENTER_Y + size);
        triangle3.setColor1(Color.RED);
        triangle3.setColor2(Color.CLEAR);
        triangle3.setColor3(Color.CLEAR);

        triangle4 = new SDTriangle(
                CENTER_X - size, CENTER_Y - size,
                CENTER_X, CENTER_Y,
                CENTER_X - size, CENTER_Y + size);
        triangle4.setColor(Color.YELLOW);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        drawMask(spriteBatch);
        drawMasked(spriteBatch);
        // Back to default depth test
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
    }

    private void drawMask(SpriteBatch spriteBatch) {
        // 1. Clear our depth buffer with 1.0
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);

        // 2. Set the function to LESS
        Gdx.gl.glDepthFunc(GL20.GL_LESS);

        // 3. Enable depth writing
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        // 4. Enable depth writing, disable RGBA color writing
        Gdx.gl.glColorMask(false, false, false, false);

        // 5. Render mask elements
        spriteBatch.begin();
        Gdx.gl.glDepthMask(true);

        shapeDrawer.filledCircle(CENTER_X, CENTER_Y, size);

        spriteBatch.end();
    }

    private void drawMasked(SpriteBatch spriteBatch) {
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);
        spriteBatch.begin();
        triangle1.draw(shapeDrawer, true);
        triangle2.draw(shapeDrawer, true);
        triangle3.draw(shapeDrawer, true);
        triangle4.draw(shapeDrawer, true);
        spriteBatch.end();
    }
}