package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.Movable;

public class IconedSpritedText implements Buttonable, Movable {

    private final Sprited background;
    private final Text label;
    private final Sprited icon;

    public IconedSpritedText(Sprite backgroundSprite, BitmapFont font, Sprite iconSprite) {
        background = new Sprited(backgroundSprite);
        label = new Text(font);
        label.hAlignCenter();
        label.setVerticallyCentered(true);
        icon = new Sprited(iconSprite);
    }

    public void draw(SpriteBatch spriteBatch) {
        background.draw(spriteBatch);
        label.draw(spriteBatch);
        icon.draw(spriteBatch);
    }

    @Override
    public boolean contains(float x, float y) {
        return background.getBoundingRectangle().contains(x, y);
    }

    @Override
    public float getX() {
        return background.getX();
    }

    @Override
    public void translateX(float amount) {
        background.translateX(amount);
        label.translateX(amount);
        icon.translateX(amount);
    }

    @Override
    public void setX(float x) {
        background.setX(x);
        label.setX(x);
        icon.setX(x + background.getWidth() - background.getHeight() / 2f - icon.getWidth() / 2f);
    }

    @Override
    public float getY() {
        return background.getY();
    }

    @Override
    public void translateY(float amount) {
        background.translateY(amount);
        label.translateY(amount);
        icon.translateY(amount);
    }

    @Override
    public void setY(float y) {
        background.setY(y);
        label.setY(y + background.getHeight() / 2f);
        icon.setY(y + background.getHeight() / 2f - icon.getHeight() / 2f);
    }

    public void setSize(float size) {
        setSize(size, size);
    }

    public void setSize(float width, float height) {
        background.setSize(width, height);
        label.setWidth(width - height);
        label.setY(background.getY() + height / 2f);
        icon.setSize(height * 0.65f);
        icon.setX(background.getX() + width - height / 2f - icon.getWidth() / 2f);
        icon.setY(background.getY() + height / 2f - icon.getHeight() / 2f);
    }

    public void setBackgroundColor(Color color) {
        background.setColor(color);
    }

    public void setTextColor(Color color) {
        label.setColor(color);
    }

    public void setIconColor(Color color) {
        icon.setColor(color);
    }

    public void setColor(Color color) {
        setBackgroundColor(color);
        setTextColor(color);
        setIconColor(color);
    }

    public String getText() {
        return label.getText();
    }

    public void setText(String text) {
        label.setText(text);
    }
}