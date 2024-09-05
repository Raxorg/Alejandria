package com.epicness.alejandria.showcase.modules.masking;

import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.alejandria.showcase.stuff.modules.masking.SDTriangle;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;

public class ShapeDrawerMaskingDrawable implements ModuleDrawable {

    private final SDTriangle triangle1, triangle2, triangle3, triangle4;
    private final Circle mask;

    public ShapeDrawerMaskingDrawable() {
        float size = 200f;

        triangle1 = new SDTriangle(
            VIEWPORT_HALF_WIDTH - size, VIEWPORT_HALF_HEIGHT - size,
            VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT,
            VIEWPORT_HALF_WIDTH + size, VIEWPORT_HALF_HEIGHT - size);
        triangle1.setColor(Color.BLUE);

        triangle2 = new SDTriangle(
            VIEWPORT_HALF_WIDTH + size, VIEWPORT_HALF_HEIGHT + size,
            VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT,
            VIEWPORT_HALF_WIDTH + size, VIEWPORT_HALF_HEIGHT - size);
        triangle2.setColor(Color.GREEN);

        triangle3 = new SDTriangle(
            VIEWPORT_HALF_WIDTH - size, VIEWPORT_HALF_HEIGHT + size,
            VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT,
            VIEWPORT_HALF_WIDTH + size, VIEWPORT_HALF_HEIGHT + size);
        triangle3.setColor1(Color.RED);
        triangle3.setColor2(Color.CLEAR);
        triangle3.setColor3(Color.CLEAR);

        triangle4 = new SDTriangle(
            VIEWPORT_HALF_WIDTH - size, VIEWPORT_HALF_HEIGHT - size,
            VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT,
            VIEWPORT_HALF_WIDTH - size, VIEWPORT_HALF_HEIGHT + size);
        triangle4.setColor(Color.YELLOW);

        mask = new Circle(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT - size, size);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        drawMask(spriteBatch, shapeDrawer);
        drawMasked(spriteBatch, shapeDrawer);
        // Back to default depth test
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
    }

    private void drawMask(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
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

        mask.draw(shapeDrawer);

        spriteBatch.end();
    }

    private void drawMasked(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);
        spriteBatch.begin();
        triangle1.draw(shapeDrawer, true);
        triangle2.draw(shapeDrawer, true);
        triangle3.draw(shapeDrawer, true);
        triangle4.draw(shapeDrawer, true);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        mask.draw(shapeRenderer);
    }

    public Circle getMask() {
        return mask;
    }
}