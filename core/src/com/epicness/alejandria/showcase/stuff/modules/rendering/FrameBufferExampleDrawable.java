package com.epicness.alejandria.showcase.stuff.modules.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.Drawable;

public class FrameBufferExampleDrawable implements Drawable {

    private final FrameBuffer fbo;
    private boolean drawDirect;

    public FrameBufferExampleDrawable() {
        int width = Gdx.graphics.getBackBufferWidth();
        int height = Gdx.graphics.getBackBufferHeight();
        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        if (drawDirect) {
            someDrawing(spriteBatch);
        } else {
            someFBODrawing(spriteBatch);
        }
    }

    private void someDrawing(SpriteBatch spriteBatch) {
        ScreenUtils.clear(Color.WHITE);

        spriteBatch.begin();
        // Drawing
        spriteBatch.end();
    }

    private void someFBODrawing(SpriteBatch spriteBatch) {
        fbo.bind();
        someDrawing(spriteBatch);
        fbo.end();

        Texture texture = fbo.getColorBufferTexture();

        ScreenUtils.clear(Color.BLACK);

        spriteBatch.begin();
        spriteBatch.draw(texture, 0, texture.getHeight(), texture.getWidth(), -texture.getHeight());
        spriteBatch.end();
    }

    public void toggleDrawDirect() {
        drawDirect = !drawDirect;
    }
}