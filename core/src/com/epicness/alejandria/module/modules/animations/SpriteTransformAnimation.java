package com.epicness.alejandria.module.modules.animations;

import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;
import static com.epicness.alejandria.ModuleID.SPRITE_TRANSFORM_ANIMATION;
import static com.epicness.fundamentals.SharedConstants.WEIRD_SHAPE_PATH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.module.modules.Module;

public class SpriteTransformAnimation extends Module {

    private SpriteBatch spriteBatch;
    private Sprite sprite1, sprite2;
    private float time;

    public SpriteTransformAnimation() {
        super(SPRITE_TRANSFORM_ANIMATION);
    }

    @Override
    public void setup() {
        spriteBatch = new SpriteBatch();

        sprite1 = new Sprite(new Texture(WEIRD_SHAPE_PATH));
        float x1 = INITIAL_WINDOW_SIZE / 3f;
        float y1 = INITIAL_WINDOW_SIZE / 3f;
        sprite1.setOriginCenter();
        sprite1.setOriginBasedPosition(x1, y1);

        sprite2 = new Sprite(sprite1);
        sprite2.setPosition(x1 - sprite1.getWidth() * 1.5f, y1 - sprite1.getHeight() * 1.5f);
        sprite2.setOrigin(x1, y1);
    }

    @Override
    public void update(float delta) {
        time += delta / 5f;
        if (time >= 1f) {
            time = 0f;
        }
        float rotation = MathUtils.map(0f, 1f, 0f, 360f, time);
        sprite1.setRotation(rotation);
        sprite2.setRotation(rotation);
    }

    @Override
    public void draw() {
        spriteBatch.begin();
        sprite1.draw(spriteBatch);
        sprite2.draw(spriteBatch);
        spriteBatch.end();
    }
}