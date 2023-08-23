package com.epicness.alejandria.showcase.modules.bullets;

import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.BARREL_LENGTH;
import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.BULLET_SPEED;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.utils.AngleUtils;
import com.epicness.fundamentals.utils.Random;

public class BulletSpawning extends Module<BulletSpawningModuleDrawable> {

    private Vector2 bulletSpeed;

    public BulletSpawning() {
        super("Bullet Spawning", "Click the screen for pew pew!");
    }

    @Override
    public BulletSpawningModuleDrawable setup() {
        bulletSpeed = new Vector2();
        return new BulletSpawningModuleDrawable(assets.getGun(), sharedAssets.getGlow());
    }

    @Override
    public void mouseMoved(float x, float y) {
        Sprited gun = drawable.getGun();
        float rotation = AngleUtils.degreesBetweenPoints(x, y, gun.getOriginBasedX(), gun.getOriginBasedY());
        gun.setRotation(rotation);
    }

    @Override
    public void touchDown(float x, float y) {
        Sprited gun = drawable.getGun();
        Sprited bullet = drawable.getBullet();

        float cos = MathUtils.cosDeg(gun.getRotation());
        float sin = MathUtils.sinDeg(gun.getRotation());

        float muzzleX = gun.getOriginBasedX() + cos * BARREL_LENGTH;
        float muzzleY = gun.getOriginBasedY() + sin * BARREL_LENGTH;

        bullet.setOriginBasedPosition(muzzleX, muzzleY);
        bullet.setColor(Random.opaqueColor());

        bulletSpeed.x = cos * BULLET_SPEED;
        bulletSpeed.y = sin * BULLET_SPEED;
    }

    @Override
    public void update(float delta) {
        drawable.getBullet().translate(bulletSpeed.x * delta, bulletSpeed.y * delta);
    }
}