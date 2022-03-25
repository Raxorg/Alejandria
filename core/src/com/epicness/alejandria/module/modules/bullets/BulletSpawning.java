package com.epicness.alejandria.module.modules.bullets;

import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;
import static com.epicness.alejandria.ModuleID.BULLET_SPAWNING;
import static com.epicness.alejandria.module.assets.ModuleAssetPaths.GUN_PATH;
import static com.epicness.fundamentals.SharedConstants.GLOW_PATH;
import static com.epicness.fundamentals.SharedConstants.GRASS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.module.modules.Module;
import com.epicness.fundamentals.utils.AngleUtils;

public class BulletSpawning extends Module {

    private SpriteBatch spriteBatch;
    private Sprite gun, bullet;
    private Vector2 bulletSpeed;

    public BulletSpawning() {
        super(BULLET_SPAWNING);
    }

    @Override
    public void setup() {
        spriteBatch = new SpriteBatch();
        // Gun
        gun = new Sprite(new Texture(GUN_PATH));
        gun.setOriginCenter();
        gun.setOriginBasedPosition(INITIAL_WINDOW_SIZE / 2f, INITIAL_WINDOW_SIZE / 2f);
        gun.setOrigin(gun.getWidth() / 2f, gun.getHeight() * 0.85f); // Gun end is higher than the center
        // Bullet
        bullet = new Sprite(new Texture(GLOW_PATH));
        bullet.setSize(gun.getHeight() / 5f, gun.getHeight() / 5f);
        bullet.setOriginCenter();
        bullet.setColor(Color.RED);

        bulletSpeed = new Vector2();
    }

    @Override
    public void update(float delta) {
        float cursorX = Gdx.input.getX();
        float cursorY = Gdx.graphics.getHeight() - Gdx.input.getY();
        float spriteX = gun.getX() + gun.getOriginX();
        float spriteY = gun.getY() + gun.getOriginY();

        gun.setRotation(AngleUtils.degreesBetweenPoints(cursorX, cursorY, spriteX, spriteY));
        bullet.translate(bulletSpeed.x * delta, bulletSpeed.y * delta);

        if (Gdx.input.justTouched()) {
            float distance = gun.getWidth() / 2f;
            // Center of sprite
            float x = gun.getX() + gun.getWidth() / 2f;
            float y = gun.getY() + gun.getHeight() * 0.85f;

            float cos = MathUtils.cosDeg(gun.getRotation());
            float sin = MathUtils.sinDeg(gun.getRotation());

            x += cos * distance;
            y += sin * distance;

            bullet.setOriginBasedPosition(x, y);

            bulletSpeed.x = cos * 200f;
            bulletSpeed.y = sin * 200f;
        }
    }

    @Override
    public void draw() {
        ScreenUtils.clear(GRASS);
        spriteBatch.begin();
        gun.draw(spriteBatch);
        bullet.draw(spriteBatch);
        spriteBatch.end();
    }
}