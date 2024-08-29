package com.epicness.standalone.masking;

import static com.epicness.fundamentals.assets.SharedAssetPaths.SPRITESLINEAR_ATLAS;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpriteBatchMaskingStandalone extends Game {

    private SpriteBatch spriteBatch;
    private Sprite mask, maskedSprite;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        TextureAtlas atlas = new TextureAtlas(SPRITESLINEAR_ATLAS.fileName);

        mask = atlas.createSprite("glow");

        maskedSprite = atlas.createSprite("weirdShape");
        maskedSprite.setColor(Color.RED);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        spriteBatch.begin();

        drawMasks();
        drawMasked();
        drawOriginals();

        spriteBatch.end();
    }

    private void drawMasks() {
        // Disable RGB color, only enable ALPHA to the frame buffer
        Gdx.gl.glColorMask(false, false, false, true);

        // Change the blending function for our alpha map
        spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);

        // Draw alpha mask Sprite(s)
        mask.draw(spriteBatch);

        // Change the blending function to remove pixels from the alpha map where alpha = 1
        spriteBatch.setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_ALPHA);

        // Remove the masked sprite's inverse alpha from the map
        maskedSprite.draw(spriteBatch);

        // Flush the batch to the GPU
        spriteBatch.flush();
    }

    private void drawMasked() {
        // Now that the buffer has our alpha, we simply draw the sprite with the mask applied
        Gdx.gl.glColorMask(true, true, true, true);

        // Change the blending function so the rendered pixels alpha blend with our alpha map
        spriteBatch.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA);

        // Draw our sprite to be masked
        maskedSprite.draw(spriteBatch);

        // Remember to flush before changing GL states again
        spriteBatch.flush();
    }

    private void drawOriginals() {
        // Default blend function
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        spriteBatch.draw(mask, 0, 256);
        spriteBatch.draw(maskedSprite, 256, 256);
    }
}