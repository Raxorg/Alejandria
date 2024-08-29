package com.epicness.fundamentals.stuff;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.SpriteDrawable;
import com.epicness.fundamentals.stuff.interfaces.Transformable;

public class Sprited implements Buttonable, Transformable, SpriteDrawable {

    private final Sprite sprite;

    public Sprited(Sprite sprite) {
        this.sprite = new Sprite(sprite);
    }

    public void setSprite(Sprite sprite) {
        this.sprite.setRegion(sprite);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }

    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        shapeRenderer.rect(getBoundingRectangle());
    }

    @Override
    public boolean contains(float x, float y) {
        return getBoundingRectangle().contains(x, y);
    }

    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    @Override
    public float getX() {
        return sprite.getX();
    }

    @Override
    public void translateX(float amount) {
        sprite.translateX(amount);
    }

    @Override
    public float getY() {
        return sprite.getY();
    }

    @Override
    public void translateY(float amount) {
        sprite.translateY(amount);
    }

    @Override
    public void stretchWidth(float amount) {
        sprite.setSize(sprite.getWidth() + amount, sprite.getHeight());
    }

    @Override
    public void stretchHeight(float amount) {
        sprite.setSize(sprite.getWidth(), sprite.getHeight() + amount);
    }

    @Override
    public float getWidth() {
        return sprite.getWidth();
    }

    @Override
    public float getHeight() {
        return sprite.getHeight();
    }

    @Override
    public float getRotation() {
        return sprite.getRotation();
    }

    @Override
    public void rotate(float degrees) {
        sprite.rotate(degrees);
    }

    public void setOriginBasedPosition(float x, float y) {
        sprite.setOriginBasedPosition(x, y);
    }

    public void setOriginBasedPosition(Vector2 position) {
        setOriginBasedPosition(position.x, position.y);
    }

    public Vector2 getOrigin(Vector2 result) {
        return result.set(sprite.getOriginX(), sprite.getOriginY());
    }

    public float getOriginBasedX() {
        return sprite.getX() + sprite.getOriginX();
    }

    public float getOriginBasedY() {
        return sprite.getY() + sprite.getOriginY();
    }

    public Vector2 getOriginBasedCenter(Vector2 result) {
        return result.set(getOriginBasedX(), getOriginBasedY());
    }

    public Vector2 getScale(Vector2 result) {
        return result.set(sprite.getScaleX(), sprite.getScaleY());
    }

    public void setScale(float scale) {
        sprite.setScale(scale);
    }

    public float getOriginX() {
        return sprite.getOriginX();
    }

    public float getOriginY() {
        return sprite.getOriginY();
    }

    public void setOrigin(float originX, float originY) {
        sprite.setOrigin(originX, originY);
    }

    public void setOriginX(float originX) {
        setOrigin(originX, sprite.getOriginY());
    }

    public void setOriginY(float originY) {
        setOrigin(sprite.getOriginX(), originY);
    }

    public void setOriginCenter() {
        sprite.setOriginCenter();
    }

    public boolean isFlipX() {
        return sprite.isFlipX();
    }

    public boolean isFlipY() {
        return sprite.isFlipY();
    }

    public void setFlip(boolean flipX, boolean flipY) {
        sprite.setFlip(flipX, flipY);
    }

    public void setFlipX(boolean flipX) {
        setFlip(flipX, sprite.isFlipY());
    }

    public void flipX() {
        setFlipX(!isFlipX());
    }

    public void setFlipY(boolean flipY) {
        setFlip(sprite.isFlipX(), flipY);
    }

    public void flipY() {
        setFlipY(!isFlipY());
    }

    public Color getColor() {
        return sprite.getColor();
    }

    public void setColor(Color color) {
        sprite.setColor(color);
    }

    public void useBilinearFilter() {
        sprite.getTexture().setFilter(Linear, Linear);
    }
}