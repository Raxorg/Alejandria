package com.epicness.fundamentals.stuff;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.Drawable;

public class SpritedAnimation implements Buttonable, Drawable {

    private final Animation<Sprited> animation;
    private float time;

    public SpritedAnimation(Sprite[] spriteFrames, float frameDuration) {
        Sprited[] animationFrames = new Sprited[spriteFrames.length];
        for (int i = 0; i < spriteFrames.length; i++) {
            animationFrames[i] = new Sprited(spriteFrames[i]);
        }
        animation = new Animation<>(frameDuration, animationFrames);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        animation.getKeyFrame(time).draw(spriteBatch);
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
        animation.getKeyFrame(time).drawDebug(shapeBatch);
    }

    @Override
    public boolean contains(float x, float y) {
        return animation.getKeyFrame(time).contains(x, y);
    }

    public Rectangle getBoundingRectangle() {
        return animation.getKeyFrames()[0].getBoundingRectangle();
    }

    public void addTime(float seconds) {
        time += seconds;
    }

    public void setY(float y) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setY(y);
        }
    }

    public void setPosition(float x, float y) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setPosition(x, y);
        }
    }

    public void setOriginBasedPosition(float x, float y) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setOriginBasedPosition(x, y);
        }
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

    public void enableLooping() {
        animation.setPlayMode(LOOP);
    }

    public void resetTime() {
        time = 0f;
    }

    public void useBilinearFilter() {
        animation.getKeyFrames()[0].useBilinearFilter();
    }
}