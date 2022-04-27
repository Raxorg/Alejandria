package com.epicness.alejandria.showcase.stuff.modules.rendering;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CENTER_X;
import static com.epicness.fundamentals.SharedConstants.CENTER_Y;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;

public class FrameBufferingDrawable implements Drawable {

    private final FrameBuffer frameBuffer, showcaseBuffer;
    private final Sprite sprite, bufferSprite;
    private boolean drawDirect;

    public FrameBufferingDrawable(Sprite weirdShapeSprite, FrameBuffer showcaseBuffer) {
        // Frame buffer size is not affected by cameras
        frameBuffer = new FrameBuffer(RGBA8888, WINDOW_SIZE, WINDOW_SIZE, false);
        this.showcaseBuffer = showcaseBuffer;

        sprite = new Sprite(weirdShapeSprite);
        sprite.setColor(Color.BLUE);
        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(CENTER_X, CENTER_Y);

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
        sprite.draw(spriteBatch);
        spriteBatch.end();
    }

    private void drawUsingFBO(SpriteBatch spriteBatch) {
        frameBuffer.bind();
        drawNormally(spriteBatch);
        frameBuffer.end();

        Texture texture = frameBuffer.getColorBufferTexture();
        bufferSprite.setRegion(texture);
        bufferSprite.flip(false, true);

        showcaseBuffer.bind();
        spriteBatch.begin();
        bufferSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    public void toggleDrawDirect() {
        drawDirect = !drawDirect;
        sprite.setColor(drawDirect ? Color.RED : Color.BLUE);
    }
}