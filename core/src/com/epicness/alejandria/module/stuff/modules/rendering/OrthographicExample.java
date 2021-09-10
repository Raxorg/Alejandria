package com.epicness.alejandria.module.stuff.modules.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.module.stuff.modules.Module;

import static com.epicness.alejandria.ModuleID.ORTHOGRAPHIC_EXAMPLE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

public class OrthographicExample extends Module {

    private Sprite weirdShape;
    private OrthographicCamera camera;

    public OrthographicExample() {
        super(ORTHOGRAPHIC_EXAMPLE);
    }

    @Override
    public void setup() {
        weirdShape = new Sprite(new Texture(Gdx.files.internal("images/weirdShape.png")));
        weirdShape.setSize(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        weirdShape.draw(spriteBatch);
    }
}