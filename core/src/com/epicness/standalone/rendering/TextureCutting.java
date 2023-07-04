package com.epicness.standalone.rendering;

import static com.epicness.fundamentals.assets.SharedAssetPaths.WEIRDSHAPE_SPRITE;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class TextureCutting extends Game {

    private SpriteBatch spriteBatch;
    private Texture texture;
    private ArrayList<Sprite> sprites;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        texture = new Texture(WEIRDSHAPE_SPRITE.fileName);
        sprites = new ArrayList<>();
        Sprite sprite1 = new Sprite(texture, texture.getWidth() / 2, texture.getHeight());
        sprites.add(sprite1);
    }

    @Override
    public void render() {
        spriteBatch.begin();

        /* Draw the original texture for reference */
        spriteBatch.draw(texture, 0, texture.getHeight());

        /* Draw all our sprites using the same texture so batching takes place */
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).draw(spriteBatch);
        }

        spriteBatch.end();

        /* Test sprite creation on the fly */
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            makeSecondSprite();
        }
    }

    private void makeSecondSprite() {
        /* Let's take the first sprite for this example */
        Sprite sprite1 = sprites.get(0);

        /* Sprites store the texture they were made of */
        Sprite sprite2 = new Sprite(sprite1.getTexture(), (int) sprite1.getWidth(), (int) sprite1.getHeight() / 2);

        /* Move it a bit for comparison */
        sprite2.setX(texture.getWidth());

        sprites.add(sprite2);
    }
}