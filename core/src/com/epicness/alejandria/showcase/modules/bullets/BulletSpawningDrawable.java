package com.epicness.alejandria.showcase.modules.bullets;

import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.BULLET_SIZE;
import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.GUN_HEIGHT;
import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.GUN_WIDTH;
import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.MUZZLE_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.interfaces.Drawable;

public class BulletSpawningDrawable implements Drawable {

    private final Sprited gun, bullet, center;

    public BulletSpawningDrawable(Sprite gunSprite, Sprite glowSprite) {
        // Gun
        gun = new Sprited(gunSprite);
        gun.setSize(GUN_WIDTH, GUN_HEIGHT);
        gun.setOrigin(GUN_WIDTH / 2f, MUZZLE_HEIGHT); // The muzzle is higher than the center
        gun.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        gun.useBilinearFilter();
        // Bullet
        bullet = new Sprited(glowSprite);
        bullet.setSize(BULLET_SIZE, BULLET_SIZE);
        bullet.setOriginCenter();
        bullet.setColor(Color.CLEAR);
        // Center
        center = new Sprited(glowSprite);
        center.setSize(BULLET_SIZE, BULLET_SIZE);
        center.setOriginCenter();
        center.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        center.setColor(Color.RED);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeRenderer) {
        spriteBatch.begin();
        gun.draw(spriteBatch);
        bullet.draw(spriteBatch);
        center.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {

    }

    public Sprited getGun() {
        return gun;
    }

    public Sprited getBullet() {
        return bullet;
    }
}