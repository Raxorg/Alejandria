package com.epicness.fundamentals;

import static com.badlogic.gdx.Input.Keys.K;
import static com.badlogic.gdx.Input.Keys.L;
import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.fundamentals.assets.SharedAssetPaths.SPRITESLINEAR_ATLAS;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.fundamentals.stuff.DualSprited;
import com.epicness.fundamentals.stuff.GradientFont;

// TODO: 9/12/2024 "Asset groups" allow you to load or dispose assets at runtime from a single asset manager
// A Game subclass like this one (not this one) should be the entry point for your game
public class FundamentalsTest extends Game {

    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private GradientFont gradientFont;
    private DualSprited dualSprited;
    private String test;
    private float fontX, degrees;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("fundamentals/fonts/pixelFont.fnt"));
        font.getData().setScale(4f);

        gradientFont = new GradientFont("Gradient Font", 20f, 500f, RED, BLUE);
        gradientFont.getData().setScale(2f);

        Sprite a = new TextureAtlas(SPRITESLINEAR_ATLAS.fileName).createSprite("weirdShape");
        dualSprited = new DualSprited(a, a);
        dualSprited.setSize(100f);

        test = "ABCDEF!GHIJKL\"MNOPQR'S\nTUVWX,YZ0123:456789?ab\ncdefghijklmnopqrstuvw\nxyz";
    }

    private void update() {
        degrees += Gdx.graphics.getDeltaTime() * 45f;
        fontX = (MathUtils.sinDeg(degrees) + 1) * 100f;
    }

    @Override
    public void render() {
        update();

        ScreenUtils.clear(Color.FOREST);
        spriteBatch.begin();
        font.draw(spriteBatch, test, fontX, 400f);
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