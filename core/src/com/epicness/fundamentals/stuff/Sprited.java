package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sprited implements Buttonable {

    private final Sprite sprite;

    public Sprited(Sprite sprite) {
        this.sprite = new Sprite(sprite);
    }

    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }

    @Override
    public boolean contains(float x, float y) {
        return sprite.getBoundingRectangle().contains(x, y);
    }

    public void setX(float x) {
        sprite.setX(x);
    }

    public float getY() {
        return sprite.getY();
    }

    public void setY(float y) {
        sprite.setY(y);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void translateY(float amount) {
        sprite.translateY(amount);
    }

    public void setSize(float size) {
        sprite.setSize(size, size);
    }

    public void setColor(Color color) {
        sprite.setColor(color);
    }
}