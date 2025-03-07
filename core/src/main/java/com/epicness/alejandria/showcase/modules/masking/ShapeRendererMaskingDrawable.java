package com.epicness.alejandria.showcase.modules.masking;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.CIRCLE_MASK_RADIUS;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.MASKED_CIRCLE_RADIUS;
import static com.epicness.fundamentals.constants.ColorConstants.DARK_DIRT;
import static com.epicness.fundamentals.constants.ColorConstants.DARK_GRASS;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.CirclePlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Triangle;

public class ShapeRendererMaskingDrawable implements ModuleDrawable {

    private final CirclePlus maskedCircle, circleMask;
    private final Triangle triangleMask;

    public ShapeRendererMaskingDrawable() {
        maskedCircle = new CirclePlus(MASKED_CIRCLE_RADIUS);
        maskedCircle.setPosition(VIEWPORT_HALF_WIDTH - MASKED_CIRCLE_RADIUS, VIEWPORT_HALF_HEIGHT - MASKED_CIRCLE_RADIUS);
        maskedCircle.setColor(DARK_DIRT);

        circleMask = new CirclePlus(CIRCLE_MASK_RADIUS);
        circleMask.setPosition(VIEWPORT_HALF_WIDTH - CIRCLE_MASK_RADIUS, VIEWPORT_HALF_HEIGHT);
        circleMask.setColor(DARK_GRASS);

        triangleMask = new Triangle(
            VIEWPORT_HALF_WIDTH - 200f, VIEWPORT_HALF_HEIGHT - 200f,
            VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT,
            VIEWPORT_HALF_WIDTH + 200f, VIEWPORT_HALF_HEIGHT - 200f
        );
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        shapeRenderer.begin();

        drawMasks(shapeRenderer);
        drawMasked(shapeRenderer);
        drawContours(shapeRenderer);

        shapeRenderer.end();
    }

    private void drawMasks(ShapeRendererPlus shapeRenderer) {
        // 1. Clear our depth buffer info from previous frame
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);

        // 2. Set the depth function to LESS
        Gdx.gl.glDepthFunc(GL20.GL_LESS);

        // 3. Enable depth writing
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        // 4. Disable RGBA color writing
        Gdx.gl.glColorMask(false, false, false, false);

        shapeRenderer.set(Filled);
        circleMask.draw(shapeRenderer);
        triangleMask.draw(shapeRenderer);
        shapeRenderer.flush();
    }

    private void drawMasked(ShapeRendererPlus shapeRenderer) {
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);

        maskedCircle.draw(shapeRenderer);
        shapeRenderer.flush();
    }

    private void drawContours(ShapeRendererPlus shapeRenderer) {
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
        shapeRenderer.set(Line);
        // The masks
        circleMask.draw(shapeRenderer);
        triangleMask.draw(shapeRenderer);
        // The masked circle
        maskedCircle.draw(shapeRenderer);
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }

    public CirclePlus getCircleMask() {
        return circleMask;
    }

    public Triangle getTriangleMask() {
        return triangleMask;
    }
}