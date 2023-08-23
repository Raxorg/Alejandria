package com.epicness.alejandria.showcase.modules.masking;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_BACKGROUND_COLOR;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_X;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_Y;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class AlphaMaskingModuleDrawable implements ModuleDrawable {

    private final Sprite maskedSprite, mask, visibleMask, originalMaskedSprite;
    private final FrameBuffer frameBuffer;
    private final Sprite bufferSprite;

    public AlphaMaskingModuleDrawable(Sprite weirdShape, Sprite glow) {
        maskedSprite = new Sprite(weirdShape);
        maskedSprite.setOriginCenter();
        maskedSprite.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        maskedSprite.setColor(RED);

        mask = new Sprite(glow);
        mask.setOriginCenter();
        mask.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);

        visibleMask = new Sprite(glow);
        visibleMask.setOriginCenter();
        visibleMask.setOriginBasedPosition(CAMERA_HALF_WIDTH - mask.getWidth() - 20f, CAMERA_HALF_HEIGHT);

        originalMaskedSprite = new Sprite(weirdShape);
        originalMaskedSprite.setOriginCenter();
        originalMaskedSprite.setOriginBasedPosition(CAMERA_HALF_WIDTH + mask.getWidth() + 20f, CAMERA_HALF_HEIGHT);
        originalMaskedSprite.setColor(RED);

        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, WINDOW_SIZE, WINDOW_SIZE, false);

        bufferSprite = new Sprite();
        bufferSprite.setSize(SHOWCASE_SIZE, SHOWCASE_SIZE);
        bufferSprite.setPosition(SHOWCASE_X, SHOWCASE_Y);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        frameBuffer.bind();
        ScreenUtils.clear(SHOWCASE_BACKGROUND_COLOR); // Draw the elements the background instead of just clearing the screen

        spriteBatch.begin();
        // Draw the alpha mask
        drawMask(spriteBatch);
        // Draw our foreground elements
        drawMasked(spriteBatch);
        // Back to default blend function
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        // Draw the sprites used
        visibleMask.draw(spriteBatch);
        originalMaskedSprite.draw(spriteBatch);
        spriteBatch.end();

        frameBuffer.end();

        bufferSprite.setRegion(frameBuffer.getColorBufferTexture());
        bufferSprite.flip(false, true);

        spriteBatch.begin();
        bufferSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    private void drawMask(SpriteBatch spriteBatch) {
        // Disable RGB color, only enable ALPHA to the frame buffer
        Gdx.gl.glColorMask(false, false, false, true);

        // Change the blending function for our alpha map
        spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);

        // Draw alpha mask sprite(s)
        mask.draw(spriteBatch);

        // Change the blending function to remove pixels from the alpha map where alpha = 1
        spriteBatch.setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_ALPHA);

        // Remove the masked sprite's inverse alpha from the map
        maskedSprite.draw(spriteBatch);

        // Flush the batch to the GPU
        spriteBatch.flush();
    }

    private void drawMasked(SpriteBatch spriteBatch) {
        // Now that the buffer has our alpha, we simply draw the sprite with the mask applied
        Gdx.gl.glColorMask(true, true, true, true);

        spriteBatch.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA);

        // Draw our sprite to be masked
        maskedSprite.draw(spriteBatch);

        // Remember to flush before changing GL states again
        spriteBatch.flush();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }
}