package com.epicness.fundamentals.stuff;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpritedAnimation extends Sprited {

    private final Animation<Sprite> animation;
    private float time;

    public SpritedAnimation(Sprite[] spriteFrames, float frameDuration) {
        super(new Sprite(spriteFrames[0]));
        Sprite[] animationFrames = new Sprite[spriteFrames.length];
        for (int i = 0; i < spriteFrames.length; i++) {
            animationFrames[i] = new Sprite(spriteFrames[i]);
        }
        animation = new Animation<>(frameDuration, animationFrames);
    }

    public void addTime(float seconds) {
        time += seconds;
        setSprite(animation.getKeyFrame(time));
    }

    @Override
    public void setPosition(float x, float y) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setPosition(x, y);
        }
    }

    @Override
    public void setSize(float size) {
        for (int i = 0; i < animation.getKeyFrames().length; i++) {
            animation.getKeyFrames()[i].setSize(size, size);
        }
    }

    public void enableLooping() {
        animation.setPlayMode(LOOP);
    }
}