package com.epicness.alejandria.showcase.stuff;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.WHITE_CLEAR_50;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.stuff.SpritedText;

public class Showcase {

    private final SpritedText topStripe;
    private final FrameBuffer frameBuffer;
    private Drawable drawable;
    private final Sprite sprite;

    public Showcase(Sprite pixel, BitmapFont font) {
        topStripe = new SpritedText(pixel, font);
        topStripe.setSize(CAMERA_WIDTH, 100f);
        topStripe.setY(CAMERA_HEIGHT - 100f);
        topStripe.setBackgroundColor(WHITE_CLEAR_50);

        frameBuffer = new FrameBuffer(RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        sprite = new Sprite();
        sprite.setSize(SHOWCASE_SIZE, SHOWCASE_SIZE);
        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        frameBuffer.bind();
        ScreenUtils.clear(Color.NAVY);
        if (drawable != null) {
            drawable.draw(spriteBatch, shapeRenderer);
        }
        frameBuffer.end();
        sprite.setRegion(frameBuffer.getColorBufferTexture());
        sprite.flip(false, true);
        spriteBatch.begin();
        topStripe.draw(spriteBatch);
        sprite.draw(spriteBatch);
        spriteBatch.end();
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setTitle(String title) {
        topStripe.setText(title);
    }
}