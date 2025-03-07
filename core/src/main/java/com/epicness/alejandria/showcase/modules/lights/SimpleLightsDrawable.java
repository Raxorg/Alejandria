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
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.CirclePlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.RectanglePlus;
import com.epicness.fundamentals.utils.Random;

import java.util.ArrayList;
import java.util.List;

public class SimpleLightsDrawable implements ModuleDrawable {

    private FrameBuffer lightBuffer;
    private final TextureRegion bufferRegion;
    private final CirclePlus[] circles;
    private final RectanglePlus[] rectangles;
    private final List<Sprite> lights;
    private final Matrix4 projectionMatrix;
    private final OrthographicCamera bufferCamera;

    public SimpleLightsDrawable() {
        lightBuffer = new FrameBuffer(RGBA8888, (int) VIEWPORT_WIDTH, (int) VIEWPORT_HEIGHT, false);
        bufferRegion = new TextureRegion(lightBuffer.getColorBufferTexture(), (int) VIEWPORT_WIDTH, (int) SHOWCASE_SIZE);
        bufferRegion.flip(false, true);

        circles = new CirclePlus[SHAPE_COUNT];
        for (int i = 0; i < circles.length; i++) {
            circles[i] = new CirclePlus(
                MathUtils.random(MIN_CIRCLE_X, MAX_CIRCLE_X),
                MathUtils.random(MIN_CIRCLE_Y, MAX_CIRCLE_Y),
                CIRCLE_RADIUS,
                Random.rainbowColor(),
                0f
            );
        }

        rectangles = new RectanglePlus[SHAPE_COUNT];
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i] = new RectanglePlus(
                MathUtils.random(MIN_RECTANGLE_X, MAX_RECTANGLE_X),
                MathUtils.random(MIN_RECTANGLE_Y, MAX_RECTANGLE_Y),
                RECTANGLE_SIZE,
                Random.rainbowColor(),
                0f
            );
        }

        lights = new ArrayList<>();
        projectionMatrix = new Matrix4();

        bufferCamera = new OrthographicCamera();
        bufferCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        // Normal drawing
        drawRandomShapes(spriteBatch, shapeDrawer);

        // Draw to the lights buffer
        drawLights(spriteBatch);

        // Draw what's on the buffer
        spriteBatch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
        spriteBatch.begin();
        spriteBatch.draw(bufferRegion, 0, SHOWCASE_STRIPE_HEIGHT);
        spriteBatch.end();

        // Back to normal blending
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    private void drawRandomShapes(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        spriteBatch.begin();
        for (int i = 0; i < circles.length; i++) {
            circles[i].draw(shapeDrawer);
        }
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i].draw(shapeDrawer);
        }
        spriteBatch.end();
    }

    private void drawLights(SpriteBatch spriteBatch) {
        // Save current projection matrix and use a projection matrix fit for the framebuffer
        projectionMatrix.set(spriteBatch.getProjectionMatrix());
        spriteBatch.setProjectionMatrix(bufferCamera.combined);

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

        // Return to the previous projection matrix
        spriteBatch.setProjectionMatrix(projectionMatrix);
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }

    public FrameBuffer getLightBuffer() {
        return lightBuffer;
    }

    public void setLightBuffer(FrameBuffer lightBuffer) {
        this.lightBuffer = lightBuffer;
    }

    public TextureRegion getBufferRegion() {
        return bufferRegion;
    }

    public List<Sprite> getLights() {
        return lights;
    }
}