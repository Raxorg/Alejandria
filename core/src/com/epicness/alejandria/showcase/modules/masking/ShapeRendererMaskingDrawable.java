package com.epicness.alejandria.showcase.modules.masking;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.DARK_DIRT;
import static com.epicness.fundamentals.SharedConstants.DARK_GRASS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.interfaces.Drawable;
import com.epicness.fundamentals.stuff.shapes.Circle;
import com.epicness.fundamentals.stuff.shapes.Triangle;

public class ShapeRendererMaskingDrawable implements Drawable {

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
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        shapeBatch.begin();

        drawMasks(shapeBatch);
        drawMasked(shapeBatch);
        drawContours(shapeBatch);

        shapeBatch.end();
    }

    private void drawMasks(ShapeBatch shapeBatch) {
        // 1. Clear our depth buffer info from previous frame
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);

        // 2. Set the depth function to LESS
        Gdx.gl.glDepthFunc(GL20.GL_LESS);

        // 3. Enable depth writing
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        // 4. Disable RGBA color writing
        Gdx.gl.glColorMask(false, false, false, false);

        shapeBatch.set(Filled);
        circleMask.draw(shapeBatch);
        triangleMask.draw(shapeBatch);
        shapeBatch.flush();
    }

    private void drawMasked(ShapeBatch shapeBatch) {
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);

        maskedCircle.draw(shapeBatch);
        shapeBatch.flush();
    }

    private void drawContours(ShapeBatch shapeBatch) {
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
        shapeBatch.set(Line);
        // The masks
        circleMask.draw(shapeBatch);
        triangleMask.draw(shapeBatch);
        // The masked circle
        maskedCircle.draw(shapeBatch);
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
    }

    public Circle getCircleMask() {
        return circleMask;
    }

    public Triangle getTriangleMask() {
        return triangleMask;
    }
}