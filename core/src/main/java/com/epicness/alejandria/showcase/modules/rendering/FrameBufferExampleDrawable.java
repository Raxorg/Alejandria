package com.epicness.alejandria.showcase.modules.rendering;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.GL20.GL_ONE;
import static com.badlogic.gdx.graphics.GL20.GL_ONE_MINUS_SRC_ALPHA;
import static com.badlogic.gdx.graphics.GL20.GL_SRC_ALPHA;
import static com.epicness.fundamentals.constants.ColorConstants.GRASS;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class FrameBufferExampleDrawable implements ModuleDrawable {

    private FrameBuffer frameBuffer;
    private final Sprite sprite1, sprite2, bufferSprite;
    private final Matrix4 projectionMatrix;
    private final OrthographicCamera bufferCamera;
    private Texture frameBufferTexture;
    private boolean drawDirect;

    public FrameBufferExampleDrawable(Sprite glowSprite) {
        // Frame buffer size is not affected by cameras
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        frameBufferTexture = frameBuffer.getColorBufferTexture();

        sprite1 = new Sprite(glowSprite);
        sprite1.setScale(2f);
        sprite1.setOriginCenter();
        sprite1.setOriginBasedPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
        sprite1.setColor(RED);

        sprite2 = new Sprite(glowSprite);
        sprite2.setScale(2f);
        sprite2.setOriginCenter();
        sprite2.setOriginBasedPosition(VIEWPORT_HALF_WIDTH + 100f, VIEWPORT_HALF_HEIGHT);

        bufferSprite = new Sprite();
        bufferSprite.setSize(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        projectionMatrix = new Matrix4();

        bufferCamera = new OrthographicCamera();
        bufferCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        if (drawDirect) {
            drawNormally(spriteBatch);
        } else {
            drawUsingFBO(spriteBatch);
        }
    }

    private void drawNormally(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        sprite1.draw(spriteBatch);
        sprite2.draw(spriteBatch);
        spriteBatch.end();
    }

    private void drawUsingFBO(SpriteBatch spriteBatch) {
        // Save current projection matrix and switch to buffer projection matrix
        projectionMatrix.set(spriteBatch.getProjectionMatrix());
        spriteBatch.setProjectionMatrix(bufferCamera.combined);

        // Draw something into the FrameBuffer
        spriteBatch.setBlendFunctionSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ONE);
        frameBuffer.bind();
        ScreenUtils.clear(GRASS); // Draw any previous elements you had rendered instead of only clearing the screen
        drawNormally(spriteBatch);
        frameBuffer.end();

        // Restore projection matrix
        spriteBatch.setProjectionMatrix(projectionMatrix);

        // Get the Texture from the FrameBuffer
        bufferSprite.setRegion(frameBufferTexture);
        bufferSprite.flip(false, true);
        // Draw the Texture
        spriteBatch.setBlendFunction(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
        spriteBatch.begin();
        bufferSprite.draw(spriteBatch);
        spriteBatch.end();
        // Return to default blending
        spriteBatch.setBlendFunction(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }

    public void toggleDrawDirect() {
        drawDirect = !drawDirect;
        sprite1.setColor(drawDirect ? BLUE : RED);
    }

    public FrameBuffer getFrameBuffer() {
        return frameBuffer;
    }

    public void setFrameBuffer(FrameBuffer frameBuffer) {
        this.frameBuffer = frameBuffer;
        frameBufferTexture = frameBuffer.getColorBufferTexture();
    }
}