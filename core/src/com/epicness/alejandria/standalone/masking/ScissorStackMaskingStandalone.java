package com.epicness.alejandria.standalone.masking;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

public class ScissorStackMaskingStandalone extends Game {

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Rectangle scissors, clipBounds;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glLineWidth(2);

        scissors = new Rectangle();
        clipBounds = new Rectangle(100, 100, 200, 200);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        shapeRenderer.begin();

        drawMasked();
        drawContours();

        shapeRenderer.end();
    }

    private void drawMasked() {
        ScissorStack.calculateScissors(camera, shapeRenderer.getTransformMatrix(), clipBounds, scissors);
        boolean pop = ScissorStack.pushScissors(scissors);

        shapeRenderer.set(Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(100, 100, 100);
        shapeRenderer.flush();

        if (pop) {
            ScissorStack.popScissors();
        }
    }

    private void drawContours() {
        shapeRenderer.set(Line);
        // The rectangle mask
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(100, 100, 200, 200);
        // The masked circle
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(100, 100, 100);
    }
}