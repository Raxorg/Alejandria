package com.epicness.fundamentals;

import static com.badlogic.gdx.Input.Keys.K;
import static com.badlogic.gdx.Input.Keys.L;
import static com.epicness.fundamentals.assets.SharedAssetPaths.WEIRDSHAPE_SPRITE;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.stuff.DualSprited;

// A Game subclass like this one (not this one) should be the entry point for your game
public class FundamentalsTest extends Game {

    SpriteBatch spriteBatch;
    BitmapFont font;
    DualSprited x;
    String test;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fundamentals/fonts/pixelFont.fnt"));
        font.getData().setScale(4f);
        Sprite a = new Sprite(new Texture(WEIRDSHAPE_SPRITE.fileName));
        x = new DualSprited(a, a);
        x.setSize(100f);
        test = "ABCDEF!GHIJKL\"MNOPQR'S\nTUVWX,YZ0123:456789?ab\ncdefghijklmnopqrstuvw\nxyz";
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.FOREST);
        spriteBatch.begin();
        font.draw(spriteBatch, test, 0f, 400f);
        x.draw(spriteBatch);
        if (Gdx.input.isKeyPressed(K)) {
            x.stretchWidth(10f);
        }
        if (Gdx.input.isKeyPressed(L)) {
            x.setWidth(100f);
        }
        spriteBatch.end();
    }
}