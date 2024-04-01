package com.epicness.standalone;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.fundamentals.assets.SharedAssetPaths.PIXEL_SPRITE;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.Sprited;

public class StickmanTests extends Game {

    private SpriteBatch spriteBatch;
    private ShapeDrawerPlus shapeDrawerPlus;
    private Sprited sprite;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        Sprite pixel = new Sprite(new Texture(PIXEL_SPRITE.fileName));
        shapeDrawerPlus = new ShapeDrawerPlus(spriteBatch, pixel);

        sprite = new Sprited(pixel);
        sprite.setSize(80f, 20f);
        sprite.setPosition(200f, 200f);
        sprite.setColor(RED);
        sprite.setOrigin(10f, 10f);
    }

    @Override
    public void render() {
        spriteBatch.begin();
        sprite.rotate(60f);
        sprite.draw(spriteBatch);
        shapeDrawerPlus.circle(sprite.getOriginBasedX(), sprite.getOriginBasedY(), 5f);

        sprite.rotate(-60f);
        sprite.draw(spriteBatch);
        shapeDrawerPlus.circle(sprite.getOriginBasedX(), sprite.getOriginBasedY(), 5f);
        spriteBatch.end();
    }
}