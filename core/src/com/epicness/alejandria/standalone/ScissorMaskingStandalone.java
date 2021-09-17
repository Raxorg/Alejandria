package com.epicness.alejandria.standalone;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

public class ScissorMaskingStandalone extends Game {

    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
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
        /* Enable scissor clipping. */
        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);

        /* Define the clipped area to be half our sprite size. */
        Gdx.gl.glScissor(100, 100, 200, 200);

        /* Draw our circle to be masked, we could also draw sprites with a SpriteBatch. */
        shapeRenderer.set(Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(100, 100, 100);

        /* Remember to flush before changing GL states again. */
        shapeRenderer.flush();

        /* Disable scissor before continuing. */
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
    }

    private void drawContours() {
        /* Draw the circle's contour for comparison. */
        shapeRenderer.set(Line);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(100, 100, 100);

        /* Draw the clipped area contour for comparison. */
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(100, 100, 200, 200);
    }
}