package com.epicness.alejandria.standalone.masking;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

public class ShapeRendererMaskingStandalone extends Game {

    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        Gdx.gl.glLineWidth(2);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        shapeRenderer.begin();

        drawMasks();
        drawMasked();
        drawContours();

        shapeRenderer.end();
    }

    private void drawMasks() {
        // 1. Clear our depth buffer info from previous frame
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);

        // 2. Set the depth function to LESS
        Gdx.gl.glDepthFunc(GL20.GL_LESS);

        // 3. Enable depth writing
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        // 4. Disable RGBA color writing
        Gdx.gl.glColorMask(false, false, false, false);

        // 5. Render mask elements
        // Put this line after SpriteBatch.begin() when using a SpriteBatch
        // Gdx.gl.glDepthMask(true);

        shapeRenderer.set(Filled);
        shapeRenderer.circle(100, 200, 100);
        shapeRenderer.triangle(0, 0, 100, 100, 200, 0);
        shapeRenderer.flush();
    }

    private void drawMasked() {
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(100, 100, 100);
        shapeRenderer.flush();
    }

    private void drawContours() {
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
        shapeRenderer.set(Line);
        // The masks
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.circle(100, 200, 100);
        shapeRenderer.triangle(0, 0, 100, 100, 200, 0);
        // The masked circle
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(100, 100, 100);
    }
}