package com.epicness.alejandria.showcase.logic.modules.bullets;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.epicness.alejandria.showcase.logic.input.BulletSpawningInput;
import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.bullets.BulletSpawningDrawable;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.utils.AngleUtils;

public class BulletSpawning extends Module {

    private BulletSpawningDrawable drawable;
    // Logic
    private Vector2 bulletSpeed;

    public BulletSpawning() {
        super("Bullet Spawning");
    }

    @Override
    public Drawable setup() {
        ShowcaseInputHandler inputHandler = (ShowcaseInputHandler) logic.getHandler(ShowcaseInputHandler.class);
        inputHandler.setModuleInputHandler(new BulletSpawningInput());

        bulletSpeed = new Vector2();

        return drawable = new BulletSpawningDrawable(assets.getGun(), sharedAssets.getGlow());
    }

    public void mouseMoved(float x, float y) {
        Sprited gun = drawable.getGun();

        float spriteX = gun.getX() + gun.getOriginX();
        float spriteY = gun.getY() + gun.getOriginY();

        gun.setRotation(AngleUtils.degreesBetweenPoints(x, y, spriteX, spriteY));
    }

    public void touchDown() {
        Sprited gun = drawable.getGun();
        Sprited bullet = drawable.getBullet();

        float distance = gun.getWidth() / 2f;
        // Center of sprite
        float centerX = gun.getX() + gun.getWidth() / 2f;
        float centerY = gun.getY() + gun.getHeight() * 0.85f;

        float cos = MathUtils.cosDeg(gun.getRotation());
        float sin = MathUtils.sinDeg(gun.getRotation());

        centerX += cos * distance;
        centerY += sin * distance;

        bullet.setOriginBasedPosition(centerX, centerY);

        bulletSpeed.x = cos * 200f;
        bulletSpeed.y = sin * 200f;
    }

    @Override
    public void update(float delta) {
        drawable.getBullet().translate(bulletSpeed.x * delta, bulletSpeed.y * delta);
    }
}