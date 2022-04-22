package com.epicness.alejandria.showcase.stuff;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CENTER_X;
import static com.epicness.fundamentals.SharedConstants.CENTER_Y;
import static com.epicness.fundamentals.SharedConstants.GRASS;
import static com.epicness.fundamentals.SharedConstants.WHITE_CLEAR_50;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.SpritedText;

public class Showcase {

    private final Sprited background;
    private final SpritedText topStripe;
    private final FrameBuffer frameBuffer;
    private Drawable drawable;
    private final Sprite moduleSprite;
    private final Sprited previous, next;

    public Showcase(Sprite pixel, BitmapFont font, Sprite arrow) {
        background = new Sprited(pixel);
        background.setSize(SHOWCASE_SIZE, SHOWCASE_SIZE);
        background.setOriginCenter();
        background.setOriginBasedPosition(CENTER_X, CENTER_Y);
        background.setColor(GRASS);

        topStripe = new SpritedText(pixel, font);
        topStripe.setSize(CAMERA_WIDTH, 100f);
        topStripe.setY(CAMERA_HEIGHT - 100f);
        topStripe.setBackgroundColor(WHITE_CLEAR_50);

        frameBuffer = new FrameBuffer(RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        moduleSprite = new Sprite();
        moduleSprite.setSize(SHOWCASE_SIZE, SHOWCASE_SIZE);
        moduleSprite.setOriginCenter();
        moduleSprite.setOriginBasedPosition(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);

        previous = new Sprited(arrow);
        previous.setSize(100f);
        previous.setOriginCenter();
        previous.rotate(180f);

        next = new Sprited(arrow);
        next.setSize(100f);
        next.setX(CAMERA_WIDTH - next.getWidth());
    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        frameBuffer.bind();
        ScreenUtils.clear(Color.CLEAR);
        if (drawable != null) {
            drawable.draw(spriteBatch, shapeRenderer);
        }
        frameBuffer.end();
        moduleSprite.setRegion(frameBuffer.getColorBufferTexture());
        moduleSprite.flip(false, true);
        spriteBatch.begin();
        background.draw(spriteBatch);
        topStripe.draw(spriteBatch);
        moduleSprite.draw(spriteBatch);
        previous.draw(spriteBatch);
        next.draw(spriteBatch);
        spriteBatch.end();
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setTitle(String title) {
        topStripe.setText(title);
    }

    public Sprited getPrevious() {
        return previous;
    }

    public Sprited getNext() {
        return next;
    }
}