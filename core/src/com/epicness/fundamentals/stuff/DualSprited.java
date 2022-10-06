package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.Scrollable;

public class DualSprited implements Buttonable, Scrollable {

    protected final Sprite background, foreground;
    private boolean backgroundButtonable;

    public DualSprited(Sprite backgroundSprite, Sprite foregroundSprite) {
        background = new Sprite(backgroundSprite);
        foreground = new Sprite(foregroundSprite);
        backgroundButtonable = true;
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
        return (backgroundButtonable && background.getBoundingRectangle().contains(x, y))
                || (!backgroundButtonable && foreground.getBoundingRectangle().contains(x, y));
    }

    public void setBackgroundButtonable(boolean backgroundButtonable) {
        this.backgroundButtonable = backgroundButtonable;
    }

    @Override
    public float getY() {
        return background.getY();
    }

    @Override
    public void setY(float y) {
        background.setY(y);
        foreground.setY(y);
    }

    @Override
    public void translateY(float amount) {
        background.translateY(amount);
        foreground.translateY(amount);
    }

    public float getX() {
        return background.getX();
    }

    public void setX(float x) {
        background.setX(x);
        foreground.setX(x);
    }

    public void setPosition(float x, float y) {
        background.setPosition(x, y);
        foreground.setPosition(x, y);
    }

    public void setPosition(Vector2 position) {
        setPosition(position.x, position.y);
    }

    public void setForegroundOriginBasedPosition(float x, float y) {
        foreground.setOriginBasedPosition(x, y);
    }

    public void setOriginBasedPosition(float x, float y) {
        background.setOriginBasedPosition(x, y);
        foreground.setOriginBasedPosition(x, y);
    }

    public float getForegroundCenterX() {
        return foreground.getX() + foreground.getWidth() / 2f;
    }

    public float getForegroundCenterY() {
        return foreground.getY() + foreground.getHeight() / 2f;
    }

    public Vector2 getForegroundCenter() {
        return new Vector2(getForegroundCenterX(), getForegroundCenterY());
    }

    public void translateX(float amount) {
        background.translateX(amount);
        foreground.translateX(amount);
    }

    public void translate(float xAmount, float yAmount) {
        translateX(xAmount);
        translateY(yAmount);
    }

    public void setBackgroundSize(float width, float height) {
        background.setSize(width, height);
    }

    public void setBackgroundSize(float size) {
        setBackgroundSize(size, size);
    }

    public void setForegroundSize(float width, float height) {
        foreground.setSize(width, height);
    }

    public void setForegroundSize(float size) {
        setForegroundSize(size, size);
    }

    public float getBackgroundWidth() {
        return background.getWidth();
    }

    public float getBackgroundHeight() {
        return background.getHeight();
    }

    public Vector2 getBackgroundSize() {
        return new Vector2(getBackgroundWidth(), getBackgroundHeight());
    }

    public void setSize(float width, float height) {
        background.setSize(width, height);
        foreground.setSize(width, height);
    }

    public void setSize(float size) {
        setSize(size, size);
    }

    public void rotateBackground(float degrees) {
        background.rotate(degrees);
    }

    public void rotateForeground(float degrees) {
        foreground.rotate(degrees);
    }

    public void rotate(float degrees) {
        rotateBackground(degrees);
        rotateForeground(degrees);
    }

    public Vector2 getBackgroundScale() {
        return new Vector2(background.getScaleX(), background.getScaleY());
    }

    public void setBackgroundScale(float scale) {
        background.setScale(scale);
    }

    public void setForegroundScale(float scale) {
        foreground.setScale(scale);
    }

    public void setScale(float scale) {
        setBackgroundScale(scale);
        setForegroundScale(scale);
    }

    public void setOrigin(float x, float y) {
        background.setOrigin(x, y);
        foreground.setOrigin(x, y);
    }

    public void setBackgroundOriginCenter() {
        background.setOriginCenter();
    }

    public void setForegroundOriginCenter() {
        foreground.setOriginCenter();
    }

    public void setOriginCenter() {
        setBackgroundOriginCenter();
        setForegroundOriginCenter();
    }

    public void centerBackgroundOnForeground() {
        background.setOriginCenter();
        background.setOriginBasedPosition(getForegroundCenterX(), getForegroundCenterY());
    }

    public void setFlip(boolean flipX, boolean flipY) {
        background.setFlip(flipX, flipY);
        foreground.setFlip(flipX, flipY);
    }

    public Color getBackgroundColor() {
        return background.getColor();
    }

    public Color getForegroundColor() {
        return foreground.getColor();
    }

    public void setBackgroundColor(Color color) {
        background.setColor(color);
    }

    public void setForegroundColor(Color color) {
        foreground.setColor(color);
    }

    public void setColor(Color color) {
        setBackgroundColor(color);
        setForegroundColor(color);
    }

    public float getBackgroundCenterX() {
        return background.getX() + background.getWidth() / 2f;
    }

    public float getBackgroundCenterY() {
        return background.getY() + background.getHeight() / 2f;
    }

    public Vector2 getBackgroundCenter() {
        return new Vector2(getBackgroundCenterX(), getBackgroundCenterY());
    }
}