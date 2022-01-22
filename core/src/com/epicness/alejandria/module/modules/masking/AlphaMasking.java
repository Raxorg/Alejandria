package com.epicness.alejandria.module.modules.masking;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
import static com.epicness.alejandria.ModuleID.ALPHA_MASKING;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.module.modules.Module;


public class AlphaMasking extends Module {

    private SpriteBatch spriteBatch;
    private Sprite sprite, alphaMask, spriteInverseAlpha;

    public AlphaMasking() {
        super(ALPHA_MASKING);
    }

    @Override
    public void setup() {
        spriteBatch = new SpriteBatch();

        Texture weirdShape = new Texture(Gdx.files.internal("images/shared/weirdShape.png"));
        Texture glow = new Texture(Gdx.files.internal("images/masking/glow2.png"));
        Texture weirdShapeInvertedAlpha = new Texture(Gdx.files.internal("images/masking/weirdShapeInvertedAlpha.png"));

        sprite = new Sprite(weirdShape);
        sprite.setColor(Color.RED);

        alphaMask = new Sprite(glow);

        spriteInverseAlpha = new Sprite(weirdShapeInvertedAlpha);
        spriteInverseAlpha.getTexture().setFilter(Linear, Linear);
    }

    @Override
    public void draw() {
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

        spriteInverseAlpha.draw(spriteBatch);

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
        sprite.draw(spriteBatch);

        // Remember to flush before changing GL states again
        spriteBatch.flush();

        // Disable scissor before continuing
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
    }

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
        alphaMask.getTexture().dispose();
        spriteInverseAlpha.getTexture().dispose();
    }
}