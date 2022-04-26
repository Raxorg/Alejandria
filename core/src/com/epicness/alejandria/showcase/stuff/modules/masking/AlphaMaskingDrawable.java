package com.epicness.alejandria.showcase.stuff.modules.masking;

import static com.epicness.fundamentals.SharedConstants.CENTER_X;
import static com.epicness.fundamentals.SharedConstants.CENTER_Y;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class AlphaMaskingDrawable implements Drawable {

    private final Sprited maskedSprite, mask;

    public AlphaMaskingDrawable(Sprite weirdShape, Sprite glow) {
        maskedSprite = new Sprited(weirdShape);
        maskedSprite.setOriginCenter();
        maskedSprite.setOriginBasedPosition(CENTER_X, CENTER_Y);
        maskedSprite.setColor(Color.RED);

        mask = new Sprited(glow);
        mask.setOriginCenter();
        mask.setOriginBasedPosition(CENTER_X, CENTER_Y);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        // Draw the alpha mask
        drawMask(spriteBatch);
        // Draw our foreground elements
        drawMasked(spriteBatch);

        spriteBatch.end();
        // Back to default blend function
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
}