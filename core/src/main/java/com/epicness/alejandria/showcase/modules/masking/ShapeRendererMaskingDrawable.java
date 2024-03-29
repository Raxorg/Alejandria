package com.epicness.alejandria.showcase.modules.masking;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.DARK_DIRT;
import static com.epicness.fundamentals.constants.SharedConstants.DARK_GRASS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Triangle;

public class ShapeRendererMaskingDrawable implements ModuleDrawable {

    private final Circle maskedCircle, circleMask;
    private final Triangle triangleMask;

    public ShapeRendererMaskingDrawable() {
        maskedCircle = new Circle(200f);
        maskedCircle.setPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        maskedCircle.setColor(DARK_DIRT);

        circleMask = new Circle(100f);
        circleMask.setPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT + 100f);
        circleMask.setColor(DARK_GRASS);

        triangleMask = new Triangle(
            CAMERA_HALF_WIDTH - 200f, CAMERA_HALF_HEIGHT - 200f,
            CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT,
            CAMERA_HALF_WIDTH + 200f, CAMERA_HALF_HEIGHT - 200f
        );
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
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
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public Circle getCircleMask() {
        return circleMask;
    }

    public Triangle getTriangleMask() {
        return triangleMask;
    }
}