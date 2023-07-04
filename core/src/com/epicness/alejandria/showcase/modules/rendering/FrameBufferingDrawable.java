package com.epicness.alejandria.showcase.modules.rendering;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.GL20.GL_ONE;
import static com.badlogic.gdx.graphics.GL20.GL_ONE_MINUS_SRC_ALPHA;
import static com.badlogic.gdx.graphics.GL20.GL_SRC_ALPHA;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.GRASS;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.Drawable;

public class FrameBufferingDrawable implements Drawable {

    private final FrameBuffer frameBuffer;
    private final Sprite sprite1, sprite2, bufferSprite;
    private boolean drawDirect;

    public FrameBufferingDrawable(Sprite glowSprite) {
        // Frame buffer size is not affected by cameras
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, WINDOW_SIZE, WINDOW_SIZE, false);

        sprite1 = new Sprite(glowSprite);
        sprite1.setScale(2f);
        sprite1.setOriginCenter();
        sprite1.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        sprite1.setColor(RED);

        sprite2 = new Sprite(glowSprite);
        sprite2.setScale(2f);
        sprite2.setOriginCenter();
        sprite2.setOriginBasedPosition(CAMERA_HALF_WIDTH + 100f, CAMERA_HALF_HEIGHT);

        bufferSprite = new Sprite();
        bufferSprite.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
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
        // Draw something into the FrameBuffer
        spriteBatch.setBlendFunctionSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ONE);
        frameBuffer.bind();
        ScreenUtils.clear(GRASS); // Draw any previous elements you had rendered instead of only clearing the screen
        drawNormally(spriteBatch);
        frameBuffer.end();
        // Get the Texture from the FrameBuffer
        Texture texture = frameBuffer.getColorBufferTexture();
        bufferSprite.setRegion(texture);
        bufferSprite.flip(false, true);
        // Draw the Texture
        spriteBatch.setBlendFunction(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
        spriteBatch.begin();
        bufferSprite.draw(spriteBatch);
        spriteBatch.end();
        // Return to default blending
        spriteBatch.setBlendFunction(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public void toggleDrawDirect() {
        drawDirect = !drawDirect;
        sprite1.setColor(drawDirect ? BLUE : RED);
    }
}