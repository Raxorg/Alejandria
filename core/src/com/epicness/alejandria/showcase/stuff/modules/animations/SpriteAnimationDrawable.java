package com.epicness.alejandria.showcase.stuff.modules.animations;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.SpritedAnimation;

public class SpriteAnimationDrawable implements Drawable {

    private final SpritedAnimation animation;

    public SpriteAnimationDrawable(Sprite pixel, Sprite weirdShape) {
        Sprite[] sprites = new Sprite[2];
        sprites[0] = new Sprite(pixel);
        sprites[0].setSize(100f, 100f);
        sprites[1] = new Sprite(weirdShape);
        sprites[1].setSize(100f, 100f);
        animation = new SpritedAnimation(sprites, 0.1f);
        animation.enableLooping();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        animation.draw(spriteBatch);
    }

    public SpritedAnimation getAnimation() {
        return animation;
    }
}