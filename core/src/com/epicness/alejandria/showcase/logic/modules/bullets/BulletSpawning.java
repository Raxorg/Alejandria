package com.epicness.alejandria.showcase.logic.modules.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.bullets.BulletSpawningDrawable;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.utils.AngleUtils;

public class BulletSpawning extends Module {

    private BulletSpawningDrawable drawable;

    private Vector2 bulletSpeed;

    @Override
    public void setup() {
        drawable = new BulletSpawningDrawable(assets.getGun(), sharedAssets.getGlow());
        stuff.getShowcase().setDrawable(drawable);

        bulletSpeed = new Vector2();
    }

    @Override
    public void update(float delta) {
        Sprited gun = drawable.getGun();
        Sprited bullet = drawable.getBullet();

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
}