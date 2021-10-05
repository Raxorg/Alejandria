package com.epicness.alejandria.standalone.masking;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

public class GLScissorMaskingStandalone extends Game {

    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        /* We can use a SpriteBatch or a ShapeRenderer to draw our masked elements. */
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        /* Increase the OpenGL line thickness for better visualization. */
        Gdx.gl.glLineWidth(2);
    }

    @Override
    public void render() {
        shapeRenderer.begin();

        drawMasked();
        drawContours();

        shapeRenderer.end();
    }

    private void drawMasked() {
        /* To activate the scissor test, first enable the GL_SCISSOR_TEST enumerator.
         * Once enabled, pixels outside of the scissor box will be discarded. */
        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);

        /* To define the scissor box, use this function: */
        Gdx.gl.glScissor(100, 100, 200, 200);
        /* The x and y is the window-space lower-left position of the scissor box,
         * and width and height define the size of the rectangle. */

        /* Draw our circle to be masked, we could also draw sprites with a SpriteBatch. */
        shapeRenderer.set(Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(100, 100, 100);

        /* Remember to flush before changing GL states again. */
        shapeRenderer.flush();

        /* Deactivate the scissor test before continuing with further rendering operations. */
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
    }

    private void drawContours() {
        shapeRenderer.set(Line);

        /* Draw the circle's contour for comparison. */
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(100, 100, 100);

        /* Draw the clipped area contour for comparison. */
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(100, 100, 200, 200);
    }
}