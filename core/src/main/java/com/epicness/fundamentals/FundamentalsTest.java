package com.epicness.fundamentals;

import static com.badlogic.gdx.Input.Keys.K;
import static com.badlogic.gdx.Input.Keys.L;
import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.fundamentals.assets.SharedAssetPaths.SPRITES_ATLAS;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.stuff.DualSprited;
import com.epicness.fundamentals.stuff.GradientFont;

// A Game subclass like this one (not this one) should be the entry point for your game
public class FundamentalsTest extends Game {

    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private GradientFont gradientFont;
    private DualSprited dualSprited;
    private String test;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("fundamentals/fonts/pixelFont.fnt"));
        font.getData().setScale(4f);

        gradientFont = new GradientFont("Gradient Font", 20f, 500f, RED, BLUE);
        gradientFont.getData().setScale(2f);

        Sprite a = new TextureAtlas(SPRITES_ATLAS.fileName).createSprite("weirdShape");
        dualSprited = new DualSprited(a, a);
        dualSprited.setSize(100f);

        test = "ABCDEF!GHIJKL\"MNOPQR'S\nTUVWX,YZ0123:456789?ab\ncdefghijklmnopqrstuvw\nxyz";
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.FOREST);
        spriteBatch.begin();
        font.draw(spriteBatch, test, 0f, 400f);
        gradientFont.drawGradient(spriteBatch);
        dualSprited.draw(spriteBatch);
        if (Gdx.input.isKeyPressed(K)) {
            dualSprited.stretchWidth(10f);
        }
        if (Gdx.input.isKeyPressed(L)) {
            dualSprited.setWidth(100f);
        }
        spriteBatch.end();
    }
}