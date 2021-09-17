package com.epicness.alejandria.module.stuff.modules.rendering;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.ScreenUtils;

public class FrameBufferExample extends Game {

    private FrameBuffer fbo;
    private SpriteBatch spriteBatch;
    private boolean drawDirect;

    @Override
    public void create() {
        int width = Gdx.graphics.getBackBufferWidth();
        int height = Gdx.graphics.getBackBufferHeight();
        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);

        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) drawDirect = !drawDirect;

        if (drawDirect) {
            someDrawing();
        } else {
            someFBODrawing();
        }
    }

    private void someDrawing() {
        ScreenUtils.clear(Color.WHITE);

        spriteBatch.begin();
        // Drawing
        spriteBatch.end();
    }

    private void someFBODrawing() {
        fbo.bind();
        someDrawing();
        fbo.end();

        Texture texture = fbo.getColorBufferTexture();

        ScreenUtils.clear(Color.BLACK);

        spriteBatch.begin();
        spriteBatch.draw(texture, 0, texture.getHeight(), texture.getWidth(), -texture.getHeight());
        spriteBatch.end();
    }
}