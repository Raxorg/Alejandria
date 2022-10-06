package com.epicness.alejandria.showcase.stuff;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_BACKGROUND_COLOR;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.GRASS;
import static com.epicness.fundamentals.SharedConstants.WHITE_CLEAR_25;

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
    private final Sprited bottomStripe, previous, infoButton, next;
    private final SpritedText information;

    public Showcase(Sprite pixel, BitmapFont font, Sprite arrow, Sprite infoSprite) {
        background = new Sprited(pixel);
        background.setSize(SHOWCASE_SIZE, SHOWCASE_SIZE);
        background.setOriginCenter();
        background.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        background.setColor(GRASS);

        topStripe = new SpritedText(pixel, font);
        topStripe.setSize(CAMERA_WIDTH, 100f);
        topStripe.setY(CAMERA_HEIGHT - 100f);
        topStripe.setBackgroundColor(WHITE_CLEAR_25);

        frameBuffer = new FrameBuffer(RGBA8888, WINDOW_SIZE, WINDOW_SIZE, true);

        moduleSprite = new Sprite();
        moduleSprite.setSize(SHOWCASE_SIZE, SHOWCASE_SIZE);
        moduleSprite.setOriginCenter();
        moduleSprite.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);

        bottomStripe = new Sprited(pixel);
        bottomStripe.setSize(CAMERA_WIDTH, 100f);
        bottomStripe.setColor(WHITE_CLEAR_25);

        previous = new Sprited(arrow);
        previous.setSize(100f);
        previous.setOriginCenter();
        previous.rotate(180f);

        infoButton = new Sprited(infoSprite);
        infoButton.setSize(100f);
        infoButton.setX(CAMERA_HALF_WIDTH - infoButton.getWidth() / 2f);

        next = new Sprited(arrow);
        next.setSize(100f);
        next.setX(CAMERA_WIDTH - next.getWidth());

        information = new SpritedText(pixel, font);
        information.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        frameBuffer.bind();
        ScreenUtils.clear(SHOWCASE_BACKGROUND_COLOR);
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
        bottomStripe.draw(spriteBatch);
        previous.draw(spriteBatch);
        infoButton.draw(spriteBatch);
        next.draw(spriteBatch);
        information.draw(spriteBatch);
        spriteBatch.end();
    }

    public FrameBuffer getFrameBuffer() {
        return frameBuffer;
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

    public Sprited getInfoButton() {
        return infoButton;
    }

    public Sprited getNext() {
        return next;
    }

    public SpritedText getInformation() {
        return information;
    }
}