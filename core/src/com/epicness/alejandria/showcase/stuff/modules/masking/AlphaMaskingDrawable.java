package com.epicness.alejandria.showcase.stuff.modules.masking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class AlphaMaskingDrawable implements Drawable {

    private final Sprited sprited, alphaMask;

    public AlphaMaskingDrawable(Sprite weirdShape, Sprite glow) {
        sprited = new Sprited(weirdShape);
        weirdShape.setColor(Color.RED);

        alphaMask = new Sprited(glow);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        ScreenUtils.clear(Color.BLACK);

        spriteBatch.begin();
        // Draw the alpha mask
        drawAlphaMask(spriteBatch);
        // Draw our foreground elements
        drawForeground(spriteBatch);

        spriteBatch.end();
        // Back to default blend function
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    private void drawAlphaMask(SpriteBatch spriteBatch) {
        // Disable RGB color, only enable ALPHA to the frame buffer
        Gdx.gl.glColorMask(false, false, false, true);

        // Change the blending function for our alpha map
        spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);

        // Draw alpha mask sprite(s)
        alphaMask.draw(spriteBatch);

        spriteBatch.setBlendFunction(GL20.GL_ZERO, GL20.GL_ONE_MINUS_SRC_ALPHA);

        sprited.draw(spriteBatch);

        // Flush the batch to the GPU
        spriteBatch.flush();
    }

    private void drawForeground(SpriteBatch spriteBatch) {
        // Now that the buffer has our alpha, we simply draw the sprite with the mask applied
        Gdx.gl.glColorMask(true, true, true, true);

        spriteBatch.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA);

        // The scissor test is optional, but it depends
        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        // Gdx.gl.glScissor(25, 50, 250, 250);

        // Draw our sprite to be masked
        sprited.draw(spriteBatch);

        // Remember to flush before changing GL states again
        spriteBatch.flush();

        // Disable scissor before continuing
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
    }
}