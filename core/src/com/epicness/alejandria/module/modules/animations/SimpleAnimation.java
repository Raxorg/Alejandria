package com.epicness.alejandria.module.modules.animations;

import static com.epicness.alejandria.ModuleID.SIMPLE_ANIMATION;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.module.modules.Module;

public class SimpleAnimation extends Module {

    private SpriteBatch spriteBatch;
    private Animation<Sprite> animation;
    private float time;

    public SimpleAnimation() {
        super(SIMPLE_ANIMATION);
    }

    @Override
    public void setup() {
        spriteBatch = new SpriteBatch();

        Sprite[] sprites = new Sprite[2];
        sprites[0] = new Sprite(new Texture(Gdx.files.internal("images/pixel.png")));
        sprites[0].setSize(100f, 100f);
        sprites[1] = new Sprite(new Texture(Gdx.files.internal("images/weirdShape.png")));
        sprites[1].setSize(100f, 100f);
        animation = new Animation<>(0.1f, sprites);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float delta) {
        time += delta;
    }

    @Override
    public void draw() {
        animation.getKeyFrame(time).draw(spriteBatch);
    }
}