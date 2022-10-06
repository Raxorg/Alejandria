package com.epicness.alejandria.showcase.stuff.modules.animations;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.SpritedAnimation;

public class SpriteAnimationDrawable implements Drawable {

    private final SpritedAnimation animation;

    public SpriteAnimationDrawable(Sprite[] animationFrames) {
        animation = new SpritedAnimation(animationFrames, 0.05f);
        animation.setScale(2f);
        animation.setOriginCenter();
        animation.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        animation.enableLooping();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        animation.draw(spriteBatch);
        spriteBatch.end();
    }

    public SpritedAnimation getAnimation() {
        return animation;
    }
}