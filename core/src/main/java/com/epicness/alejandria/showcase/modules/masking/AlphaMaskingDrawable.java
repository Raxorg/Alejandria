package com.epicness.alejandria.showcase.modules.masking;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.EQUALS_X;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.EQUALS_Y;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.MASKED_SPRITE_X;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.MASKED_SPRITE_Y;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.MASK_X;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.MASK_Y;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.PLUS_X;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.PLUS_Y;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_BACKGROUND_COLOR;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class AlphaMaskingDrawable implements ModuleDrawable {

    private final Sprite maskedSprite, plus, mask, visibleMask, equals, originalSprite;
    private FrameBuffer frameBuffer;
    private final Matrix4 projection;
    private final OrthographicCamera bufferCamera;
    private final Sprite bufferSprite;

    public AlphaMaskingDrawable(Sprite weirdShape, Sprite glow, Sprite addSprite, Sprite equalSprite) {
        maskedSprite = new Sprite(weirdShape);
        maskedSprite.setOriginCenter();
        maskedSprite.setOriginBasedPosition(MASKED_SPRITE_X, MASKED_SPRITE_Y);
        maskedSprite.setColor(RED);

        plus = new Sprite(addSprite);
        plus.setSize(200f, 200f);
        plus.setOriginCenter();
        plus.setOriginBasedPosition(PLUS_X, PLUS_Y);

        equals = new Sprite(equalSprite);
        equals.setSize(200f, 200f);
        equals.setOriginCenter();
        equals.setOriginBasedPosition(EQUALS_X, EQUALS_Y);

        mask = new Sprite(glow);
        mask.setOriginCenter();
        mask.setOriginBasedPosition(MASK_X, MASK_Y);

        visibleMask = new Sprite(glow);
        visibleMask.setOriginCenter();
        visibleMask.setOriginBasedPosition(VIEWPORT_HALF_WIDTH + mask.getWidth(), CAMERA_HEIGHT * 0.75f);

        originalSprite = new Sprite(weirdShape);
        originalSprite.setOriginCenter();
        originalSprite.setOriginBasedPosition(VIEWPORT_HALF_WIDTH - mask.getWidth(), CAMERA_HEIGHT * 0.75f);
        originalSprite.setColor(RED);

        // Frame buffer is needed because normal rendering ignores or ruins blending commands
        frameBuffer = new FrameBuffer(RGBA8888, WINDOW_SIZE, WINDOW_SIZE, false);
        projection = new Matrix4();

        bufferCamera = new OrthographicCamera();
        bufferCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        bufferSprite = new Sprite();
        bufferSprite.setSize(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        frameBuffer.bind();
        // We should bring the background's pixels instead of just clearing the screen for transparency to work properly
        ScreenUtils.clear(SHOWCASE_BACKGROUND_COLOR);

        projection.set(spriteBatch.getProjectionMatrix());
        spriteBatch.setProjectionMatrix(bufferCamera.combined);
        spriteBatch.begin();
        // Draw the alpha mask
        drawMask(spriteBatch);
        // Draw our foreground elements
        drawMasked(spriteBatch);
        // Back to default blend function
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        // Draw the sprites used
        visibleMask.draw(spriteBatch);
        plus.draw(spriteBatch);
        equals.draw(spriteBatch);
        originalSprite.draw(spriteBatch);
        spriteBatch.end();

        frameBuffer.end();

        bufferSprite.setRegion(frameBuffer.getColorBufferTexture());
        bufferSprite.flip(false, true);

        spriteBatch.setProjectionMatrix(projection);
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

    public FrameBuffer getFrameBuffer() {
        return frameBuffer;
    }

    public void setFrameBuffer(FrameBuffer frameBuffer) {
        this.frameBuffer = frameBuffer;
    }
}