package com.epicness.alejandria.showcase.modules.lights;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static com.epicness.alejandria.showcase.constants.LightsConstants.CIRCLE_RADIUS;
import static com.epicness.alejandria.showcase.constants.LightsConstants.MAX_CIRCLE_X;
import static com.epicness.alejandria.showcase.constants.LightsConstants.MAX_CIRCLE_Y;
import static com.epicness.alejandria.showcase.constants.LightsConstants.MAX_RECTANGLE_X;
import static com.epicness.alejandria.showcase.constants.LightsConstants.MAX_RECTANGLE_Y;
import static com.epicness.alejandria.showcase.constants.LightsConstants.MIN_CIRCLE_X;
import static com.epicness.alejandria.showcase.constants.LightsConstants.MIN_CIRCLE_Y;
import static com.epicness.alejandria.showcase.constants.LightsConstants.MIN_RECTANGLE_X;
import static com.epicness.alejandria.showcase.constants.LightsConstants.MIN_RECTANGLE_Y;
import static com.epicness.alejandria.showcase.constants.LightsConstants.RECTANGLE_SIZE;
import static com.epicness.alejandria.showcase.constants.LightsConstants.SHAPE_COUNT;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Rectangle;
import com.epicness.fundamentals.utils.Random;

import java.util.ArrayList;
import java.util.List;

public class SimpleLightsDrawable implements ModuleDrawable {

    private final FrameBuffer lightBuffer;
    private final TextureRegion bufferRegion;
    private final Circle[] circles;
    private final Rectangle[] rectangles;
    private final List<Sprite> lights;

    public SimpleLightsDrawable() {
        lightBuffer = new FrameBuffer(RGBA8888, (int) CAMERA_WIDTH, (int) CAMERA_HEIGHT, false);
        bufferRegion = new TextureRegion(lightBuffer.getColorBufferTexture(), (int) CAMERA_WIDTH, (int) SHOWCASE_SIZE);
        bufferRegion.flip(false, true);

        circles = new Circle[SHAPE_COUNT];
        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle(
                MathUtils.random(MIN_CIRCLE_X, MAX_CIRCLE_X),
                MathUtils.random(MIN_CIRCLE_Y, MAX_CIRCLE_Y),
                CIRCLE_RADIUS,
                Random.rainbowColor()
            );
        }

        rectangles = new Rectangle[SHAPE_COUNT];
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i] = new Rectangle(
                MathUtils.random(MIN_RECTANGLE_X, MAX_RECTANGLE_X),
                MathUtils.random(MIN_RECTANGLE_Y, MAX_RECTANGLE_Y),
                RECTANGLE_SIZE,
                Random.rainbowColor()
            );
        }

        lights = new ArrayList<>();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        // Draw random shapes like you normally would
        spriteBatch.begin();
        for (int i = 0; i < circles.length; i++) {
            circles[i].draw(shapeDrawer);
        }
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i].draw(shapeDrawer);
        }
        spriteBatch.end();

        // Draw our lights to the buffer, our shapes are affected by blending
        lightBuffer.begin();
        spriteBatch.begin();
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        ScreenUtils.clear(0.3f, 0.38f, 0.4f, 1f);
        for (int i = 0; i < lights.size(); i++) {
            lights.get(i).draw(spriteBatch);
        }
        spriteBatch.end();
        lightBuffer.end();

        // Draw what's on the buffer
        spriteBatch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
        spriteBatch.begin();
        spriteBatch.draw(bufferRegion, 0, SHOWCASE_STRIPE_HEIGHT);
        spriteBatch.end();

        // Back to normal blending
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public List<Sprite> getLights() {
        return lights;
    }
}