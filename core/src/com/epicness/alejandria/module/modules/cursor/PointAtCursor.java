package com.epicness.alejandria.module.modules.cursor;

import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;
import static com.epicness.alejandria.ModuleID.POINT_AT_CURSOR;
import static com.epicness.fundamentals.SharedConstants.WEIRD_SHAPE_PATH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.module.modules.Module;
import com.epicness.fundamentals.utils.AngleUtils;

public class PointAtCursor extends Module {

    private SpriteBatch spriteBatch;
    private Sprite sprite;

    public PointAtCursor() {
        super(POINT_AT_CURSOR);
    }

    @Override
    public void setup() {
        spriteBatch = new SpriteBatch();
        sprite = new Sprite(new Texture(WEIRD_SHAPE_PATH));
        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(INITIAL_WINDOW_SIZE / 2f, INITIAL_WINDOW_SIZE / 2f);
    }

    @Override
    public void update(float delta) {
        float cursorX = Gdx.input.getX();
        float cursorY = Gdx.graphics.getHeight() - Gdx.input.getY();
        float spriteX = sprite.getX() + sprite.getOriginX();
        float spriteY = sprite.getY() + sprite.getOriginY();

        sprite.setRotation(AngleUtils.degreesBetweenPoints(cursorX, cursorY, spriteX, spriteY));
    }

    @Override
    public void draw() {
        ScreenUtils.clear(Color.NAVY);
        spriteBatch.begin();
        sprite.draw(spriteBatch);
        spriteBatch.end();
    }
}