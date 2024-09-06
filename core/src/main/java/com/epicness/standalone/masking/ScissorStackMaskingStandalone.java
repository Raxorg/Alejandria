package com.epicness.standalone.masking;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScissorStackMaskingStandalone extends Game {

    /* Some attributes we're gonna need. */
    private ShapeRenderer shapeRenderer;
    private Rectangle scissors1, scissors2;

    @Override
    public void create() {
        /* The ScissorStack needs a camera to transform the clipping rectangles. */
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        /* We can use a SpriteBatch or a ShapeRenderer to draw our masked elements. */
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setProjectionMatrix(camera.combined);

        /* Increase the OpenGL line thickness for better visualization. */
        Gdx.gl.glLineWidth(2);

        /* scissors1 and scissors2 will store the result of calculateScissors(...).
         * clipBounds is used to define the x, y, width and height of the clipping rectangles. */
        scissors1 = new Rectangle();
        Rectangle clipBounds = new Rectangle(100, 100, 200, 200);
        ScissorStack.calculateScissors(camera, shapeRenderer.getTransformMatrix(), clipBounds, scissors1);

        scissors2 = new Rectangle();
        clipBounds.set(50f, 50f, 100f, 100f);
        ScissorStack.calculateScissors(camera, shapeRenderer.getTransformMatrix(), clipBounds, scissors2);
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
        /* Feed the ScissorStack and store whether it could push the scissors or not. */
        boolean pop1 = ScissorStack.pushScissors(scissors1);
        boolean pop2 = ScissorStack.pushScissors(scissors2);

        /* Draw the elements to be constrained to an area,
         * without masking this would render a red filled circle. */
        shapeRenderer.set(Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(100, 100, 100);
        shapeRenderer.flush();

        /* Safety check for the situations the scissor fails to be pushed to the stack
         * (happens for example when the window is minimized on desktop or the clipping
         * area is <= 0). */
        if (pop1) {
            ScissorStack.popScissors();
        }
        if (pop2) {
            ScissorStack.popScissors();
        }
    }

    /* It is also possible to push multiple rectangles. Only the pixels of the sprites
     * or shapes that are within <b>all</b> of the rectangles will be rendered.
     * Also, if your camera moves, you'll need to recalculate the scissor area afterwards. */

    private void drawContours() {
        shapeRenderer.set(Line);
        // The rectangular masks
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(100, 100, 200, 200);
        shapeRenderer.rect(50, 50, 100, 100);
        // The masked circle
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(100, 100, 100);
    }
}
