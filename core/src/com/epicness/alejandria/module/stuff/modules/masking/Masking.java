package com.epicness.alejandria.module.stuff.modules.masking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.module.stuff.modules.Module;
import com.epicness.fundamentals.assets.SharedAssets;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
import static com.epicness.alejandria.ModuleID.MASKING;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;


public class Masking extends Module {

    // Structure
    private final SharedAssets assets;
    // Module specific
    private Sprite bg, sprite, alphaMask;

    public Masking(SharedAssets assets) {
        super(MASKING);
        this.assets = assets;
    }

    @Override
    public void setup() {
        bg = new Sprite(assets.getPixel());
        bg.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
        bg.setColor(Color.PINK);
        sprite = new Sprite(assets.getWeirdShape());
        sprite.setSize(250, 250);
        sprite.setColor(Color.GREEN);
        alphaMask = new Sprite(assets.getGlow());
        alphaMask.setPosition(25, 50);
        alphaMask.setSize(250, 250);
        alphaMask.getTexture().setFilter(Linear, Linear);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        //draw background
        drawBackground(spriteBatch);

        //draw the alpha mask
        drawAlphaMask(spriteBatch);

        //draw our foreground elements
        drawForeground(spriteBatch);
    }

    private void drawBackground(SpriteBatch spriteBatch) {
        //regular blending mode
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        bg.draw(spriteBatch);

        //flush the batch to the GPU
        spriteBatch.flush();
    }

    private void drawAlphaMask(SpriteBatch spriteBatch) {
        //disable RGB color, only enable ALPHA to the frame buffer
        Gdx.gl.glColorMask(false, false, false, true);

        //change the blending function for our alpha map
        spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);

        //draw alpha mask sprite(s)
        alphaMask.draw(spriteBatch);

        //flush the batch to the GPU
        spriteBatch.flush();
    }

    private void drawForeground(SpriteBatch spriteBatch) {
        //now that the buffer has our alpha, we simply draw the sprite with the mask applied
        Gdx.gl.glColorMask(true, true, true, true);
        spriteBatch.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA);

        //The scissor test is optional, but it depends
        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        Gdx.gl.glScissor(25, 50, 250, 250);

        //draw our sprite to be masked
        sprite.draw(spriteBatch);

        //remember to flush before changing GL states again
        spriteBatch.flush();

        //disable scissor before continuing
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
    }
}