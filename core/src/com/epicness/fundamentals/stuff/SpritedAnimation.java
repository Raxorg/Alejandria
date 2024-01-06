package com.epicness.fundamentals.stuff;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.Movable;

public class SpritedAnimation implements Buttonable, Movable {

    private final Animation<Sprited> animation;
    private float time;

    public SpritedAnimation(float frameDuration, Sprite... spriteFrames) {
        Sprited[] animationFrames = new Sprited[spriteFrames.length];
        for (int i = 0; i < spriteFrames.length; i++) {
            animationFrames[i] = new Sprited(spriteFrames[i]);
        }
        animation = new Animation<>(frameDuration, animationFrames);
    }

    public void draw(SpriteBatch spriteBatch) {
        animation.getKeyFrame(time).draw(spriteBatch);
    }

    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        animation.getKeyFrame(time).drawDebug(shapeRenderer);
    }

    @Override
    public boolean contains(float x, float y) {
        return animation.getKeyFrame(time).contains(x, y);
    }

    public Rectangle getBoundingRectangle() {
        return animation.getKeyFrame(time).getBoundingRectangle();
    }

    @Override
    public float getX() {
        return animation.getKeyFrame(time).getX();
    }

    public float getCenterX() {
        return getX() + getWidth() / 2f;
    }

    public float getEndX() {
        return getX() + getWidth();
    }

    @Override
    public void translateX(float amount) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].translateX(amount);
        }
    }

    @Override
    public float getY() {
        return animation.getKeyFrame(time).getY();
    }

    public float getCenterY() {
        return getY() + getHeight() / 2f;
    }

    @Override
    public void translateY(float amount) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].translateY(amount);
        }
    }

    public void setOriginBasedPosition(float x, float y) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setOriginBasedPosition(x, y);
        }
    }

    public float getWidth() {
        return animation.getKeyFrame(time).getWidth();
    }

    public float getHeight() {
        return animation.getKeyFrame(time).getHeight();
    }

    public void setSize(float width, float height) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setSize(width, height);
        }
    }

    public void setSize(float size) {
        setSize(size, size);
    }

    public void setOriginCenter() {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setOriginCenter();
        }
    }

    public void setScale(float scale) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setScale(scale);
        }
    }

    public void setColor(Color color) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setColor(color);
        }
    }

    public boolean isFlipX() {
        return animation.getKeyFrame(time).isFlipX();
    }

    public boolean isFlipY() {
        return animation.getKeyFrame(time).isFlipY();
    }

    public void setFlip(boolean flipX, boolean flipY) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setFlip(flipX, flipY);
        }
    }

    public void setFlipX(boolean flipX) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setFlipX(flipX);
        }
    }

    public void setFlipY(boolean flipY) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setFlipY(flipY);
        }
    }

    public void enableLooping() {
        animation.setPlayMode(LOOP);
    }

    public void addTime(float seconds) {
        time += seconds;
    }

    public void resetTime() {
        time = 0f;
    }

    public void useBilinearFilter() {
        animation.getKeyFrames()[0].useBilinearFilter();
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}