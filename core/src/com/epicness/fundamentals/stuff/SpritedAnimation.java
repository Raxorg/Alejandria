package com.epicness.fundamentals.stuff;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SpritedAnimation {

    private final Animation<Sprited> animation;
    private float time;

    public SpritedAnimation(Sprite[] spriteFrames, float frameDuration) {
        Sprited[] animationFrames = new Sprited[spriteFrames.length];
        for (int i = 0; i < spriteFrames.length; i++) {
            animationFrames[i] = new Sprited(spriteFrames[i]);
        }
        animation = new Animation<>(frameDuration, animationFrames);
    }

    public void draw(SpriteBatch spriteBatch) {
        animation.getKeyFrame(time).draw(spriteBatch);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        animation.getKeyFrame(time).drawDebug(shapeRenderer);
    }

    public boolean contains(float x, float y) {
        return animation.getKeyFrame(time).contains(x, y);
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

    public void setSize(float size) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setSize(size, size);
        }
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

    public void enableLooping() {
        animation.setPlayMode(LOOP);
    }

    public void addTime(float seconds) {
        time += seconds;
    }

    public void resetTime() {
        time = 0f;
    }
}
