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

// Use this as the entry point for your game by
// renaming (refactor) this class to your game's class
public class FundamentalsTest extends Game {

    SpriteBatch spriteBatch;
    BitmapFont font;
    DualSprited x;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fundamentals/fonts/pixelFont.fnt"));
        font.getData().setScale(4f);
        Sprite a = new Sprite(new Texture(WEIRDSHAPE_SPRITE.fileName));
        x = new DualSprited(a,a);
        x.setSize(100f);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.FOREST);
        spriteBatch.begin();
        font.draw(spriteBatch, "A:B,C.D", 100f, 100f);
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