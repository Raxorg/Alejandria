package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DualSprited implements Buttonable {

    private final Sprite background, foreground;

    public DualSprited(Sprite backgroundSprite, Sprite foregroundSprite) {
        background = new Sprite(backgroundSprite);
        foreground = new Sprite(foregroundSprite);
    }

    public void drawBackground(SpriteBatch spriteBatch) {
        background.draw(spriteBatch);
    }

    public void drawForeground(SpriteBatch spriteBatch) {
        foreground.draw(spriteBatch);
    }

    public void draw(SpriteBatch spriteBatch) {
        background.draw(spriteBatch);
        foreground.draw(spriteBatch);
    }

    @Override
    public boolean contains(float x, float y) {
        return background.getBoundingRectangle().contains(x, y);
    }

    public float getX() {
        return background.getX();
    }

    public void setX(float x) {
        background.setX(x);
        foreground.setX(x);
    }

    public float getY() {
        return background.getY();
    }

    public void setY(float y) {
        background.setY(y);
        foreground.setY(y);
    }

    public void setPosition(float x, float y) {
        background.setPosition(x, y);
        foreground.setPosition(x, y);
    }

    public void translateX(float amount) {
        background.translateX(amount);
        foreground.translateX(amount);
    }

    public void translateY(float amount) {
        background.translateY(amount);
        foreground.translateY(amount);
    }

    public void setSize(float size) {
        setSize(size, size);
    }

    public void setSize(float width, float height) {
        background.setSize(width, height);
        foreground.setSize(width, height);
    }

    public Color getForegroundColor() {
        return foreground.getColor();
    }

    public void setColor(Color color) {
        setBackgroundColor(color);
        setForegroundColor(color);
    }

    public void setBackgroundColor(Color color) {
        background.setColor(color);
    }

    public void setForegroundColor(Color color) {
        foreground.setColor(color);
    }
}