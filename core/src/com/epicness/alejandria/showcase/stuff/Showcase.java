package com.epicness.alejandria.showcase.stuff;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_BACKGROUND_COLOR;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_BUTTON_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.STRIPE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.TOP_STRIPE_Y;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.WHITE_CLEAR_25;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.SpritedText;

public class Showcase {

    private final SpritedText topStripe;
    private Drawable moduleDrawable;
    private final Sprited bottomStripe, previous, infoButton, next;
    private final SpritedText information;

    public Showcase(Sprite pixel, BitmapFont font, Sprite arrow, Sprite infoSprite) {
        topStripe = new SpritedText(pixel, font);
        topStripe.setSize(CAMERA_WIDTH, STRIPE_HEIGHT);
        topStripe.setY(TOP_STRIPE_Y);
        topStripe.setBackgroundColor(WHITE_CLEAR_25);

        bottomStripe = new Sprited(pixel);
        bottomStripe.setSize(CAMERA_WIDTH, STRIPE_HEIGHT);
        bottomStripe.setColor(WHITE_CLEAR_25);

        previous = new Sprited(arrow);
        previous.setSize(SHOWCASE_BUTTON_SIZE);
        previous.setOriginCenter();
        previous.rotate(180f);

        infoButton = new Sprited(infoSprite);
        infoButton.setSize(SHOWCASE_BUTTON_SIZE);
        infoButton.setX(CAMERA_HALF_WIDTH - infoButton.getWidth() / 2f);

        next = new Sprited(arrow);
        next.setSize(SHOWCASE_BUTTON_SIZE);
        next.setX(CAMERA_WIDTH - next.getWidth());

        information = new SpritedText(pixel, font);
        information.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        ScreenUtils.clear(SHOWCASE_BACKGROUND_COLOR);

        moduleDrawable.draw(spriteBatch, shapeRenderer);

        spriteBatch.begin();
        topStripe.draw(spriteBatch);
        bottomStripe.draw(spriteBatch);
        previous.draw(spriteBatch);
        infoButton.draw(spriteBatch);
        next.draw(spriteBatch);
        information.draw(spriteBatch);
        spriteBatch.end();
    }

    public void setModuleDrawable(Drawable moduleDrawable) {
        this.moduleDrawable = moduleDrawable;
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