package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.Movable;

public class MultiSprited implements Buttonable, Movable {

    private final Array<Sprite> sprites;

    public MultiSprited(Sprite[] sprites) {
        this.sprites = new Array<>();
        for (int i = 0; i < sprites.length; i++) {
            this.sprites.add(new Sprite(sprites[i]));
        }
    }

    public MultiSprited(Sprite sprite, int count) {
        this.sprites = new Array<>();
        for (int i = 0; i < count; i++) {
            this.sprites.add(new Sprite(sprite));
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0; i < sprites.size; i++) {
            sprites.get(i).draw(spriteBatch);
        }
    }

    @Override
    public boolean contains(float x, float y) {
        return sprites.get(0).getBoundingRectangle().contains(x, y);
    }

    @Override
    public float getX() {
        return sprites.get(0).getX();
    }

    @Override
    public void translateX(float amount) {
        for (int i = 0; i < sprites.size; i++) {
            sprites.get(i).translateX(amount);
        }
    }

    public void setX(float x, int index) {
        sprites.get(index).setX(x);
    }

    @Override
    public float getY() {
        return sprites.get(0).getY();
    }

    @Override
    public void translateY(float amount) {
        for (int i = 0; i < sprites.size; i++) {
            sprites.get(i).translateY(amount);
        }
    }

    public float getWidth() {
        return sprites.get(0).getWidth();
    }

    public float getHeight() {
        return sprites.get(0).getHeight();
    }

    public void setSize(float width, float height) {
        for (int i = 0; i < sprites.size; i++) {
            sprites.get(i).setSize(width, height);
        }
    }

    public void setSize(float size) {
        setSize(size, size);
    }

    public void setScale(float scale) {
        for (int i = 0; i < sprites.size; i++) {
            sprites.get(i).setScale(scale);
        }
    }

    public boolean isFlipX() {
        return sprites.get(0).isFlipX();
    }

    public void setFlip(boolean flipX, boolean flipY) {
        for (int i = 0; i < sprites.size; i++) {
            sprites.get(i).setFlip(flipX, flipY);
        }
    }

    public Color getColor() {
        return sprites.get(0).getColor();
    }

    public void setColor(Color color) {
        for (int i = 0; i < sprites.size; i++) {
            sprites.get(i).setColor(color);
        }
    }
}