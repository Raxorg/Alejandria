package com.epicness.alejandria.module.modules.masking;

import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;
import static com.epicness.alejandria.ModuleID.ANIMATED_BACKGROUNDS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.module.modules.Module;
import com.epicness.fundamentals.stuff.AnimatedBackground;

public class AnimatedBackgrounds extends Module {

    private SpriteBatch spriteBatch;
    private AnimatedBackground background3, background2, background1;
    private OrthographicCamera camera;

    public AnimatedBackgrounds() {
        super(ANIMATED_BACKGROUNDS);
    }

    @Override
    public void setup() {
        spriteBatch = new SpriteBatch();

        Sprite pixel = new Sprite(new Texture(Gdx.files.internal("images/pixel.png")));
        Sprite weirdShape = new Sprite(new Texture(Gdx.files.internal("images/weirdShape.png")));
        camera = new OrthographicCamera(INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE);
        camera.setToOrtho(false);
        background1 = new AnimatedBackground(
                50f, 50f,
                700f, 700f,
                Color.BLUE,
                weirdShape,
                pixel,
                camera,
                15,
                15,
                15f);
        background2 = new AnimatedBackground(
                300f, 300f,
                300f, 200f,
                Color.GREEN,
                pixel,
                pixel,
                camera,
                5,
                5,
                25f);
        background3 = new AnimatedBackground(
                100f, 100f,
                400f, 300f,
                Color.RED,
                pixel,
                weirdShape,
                camera,
                10,
                10,
                50f);
    }

    @Override
    public void update(float delta) {
        background1.update(delta);
        background2.update(delta);
        background3.update(delta);
    }

    @Override
    public void draw() {
        spriteBatch.setProjectionMatrix(camera.combined);
        background1.draw(spriteBatch);
        background2.draw(spriteBatch);
        background3.draw(spriteBatch);
    }
}