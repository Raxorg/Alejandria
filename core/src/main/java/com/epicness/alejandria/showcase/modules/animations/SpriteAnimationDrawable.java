package com.epicness.alejandria.showcase.modules.animations;

import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.stuff.SpritedAnimation;

public class SpriteAnimationDrawable implements ModuleDrawable {

    private final SpritePlus frames;
    private final SpritedAnimation animation;

    public SpriteAnimationDrawable(Sprite framesSprite, Sprite[] animationFrames) {
        frames = new SpritePlus(framesSprite);
        frames.setSize(400f, 400f);
        frames.setOriginCenter();
        frames.setOriginBasedPosition(VIEWPORT_HALF_WIDTH - 220f, VIEWPORT_HALF_HEIGHT);
        frames.useBilinearFilter();

        animation = new SpritedAnimation(0.05f, animationFrames);
        animation.setScale(3f);
        animation.setOriginCenter();
        animation.setOriginBasedPosition(VIEWPORT_HALF_WIDTH + 250f, VIEWPORT_HALF_HEIGHT);
        animation.enableLooping();
        animation.useBilinearFilter();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        frames.draw(spriteBatch);
        animation.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.rectangle(frames.getBoundingRectangle());
        shapeDrawer.rectangle(animation.getBoundingRectangle());
    }

    public SpritedAnimation getAnimation() {
        return animation;
    }
}