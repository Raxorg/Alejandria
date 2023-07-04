package com.epicness.alejandria.showcase.modules.animations;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.SpritedAnimation;

public class SpriteAnimationDrawable implements Drawable {

    private final Sprited frames;
    private final SpritedAnimation animation;

    public SpriteAnimationDrawable(Sprite framesSprite, Sprite[] animationFrames) {
        frames = new Sprited(framesSprite);
        frames.setSize(400f, 400f);
        frames.setOriginCenter();
        frames.setOriginBasedPosition(CAMERA_HALF_WIDTH - 220f, CAMERA_HALF_HEIGHT);
        frames.useBilinearFilter();

        animation = new SpritedAnimation(animationFrames, 0.05f);
        animation.setScale(3f);
        animation.setOriginCenter();
        animation.setOriginBasedPosition(CAMERA_HALF_WIDTH + 250f, CAMERA_HALF_HEIGHT);
        animation.enableLooping();
        animation.useBilinearFilter();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        frames.draw(spriteBatch);
        animation.draw(spriteBatch);
        spriteBatch.end();
    }

    public SpritedAnimation getAnimation() {
        return animation;
    }
}