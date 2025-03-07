package com.epicness.standalone.masking;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class FrameBufferTintMaskingStandalone extends Game {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private BitmapFont menuItemFont;
    private FrameBuffer frameBuffer;
    private float textWidth, textHeight, textX, textY;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        spriteBatch = new SpriteBatch();

        menuItemFont = new BitmapFont();
        menuItemFont.getData().setScale(6f);

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(menuItemFont, "ONE PLAYER");

        textWidth = glyphLayout.width;
        textHeight = glyphLayout.height;
        textX = WINDOW_SIZE * 0.5f - textWidth * 0.5f;
        textY = WINDOW_SIZE * 0.5f + textHeight * 0.5f;

        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, WINDOW_SIZE, WINDOW_SIZE, false);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.RED);

        frameBuffer.bind();
        draw();
        frameBuffer.end();

        Texture texture = frameBuffer.getColorBufferTexture();
        Sprite sprite = new Sprite(texture);
        sprite.flip(false, true);

        spriteBatch.begin();
        sprite.draw(spriteBatch);
        spriteBatch.end();
    }

    private void draw() {
        spriteBatch.begin();
        menuItemFont.draw(spriteBatch, "ONE PLAYER", textX, textY);
        spriteBatch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);

        Gdx.gl.glBlendFunc(GL20.GL_DST_COLOR, GL20.GL_ZERO);

        shapeRenderer.begin();
        shapeRenderer.set(Filled);
        shapeRenderer.rect(textX - 10, textY + 10, textWidth + 20, -(textHeight + 20),
                Color.LIME, Color.LIME, Color.BLACK, Color.BLACK);
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}