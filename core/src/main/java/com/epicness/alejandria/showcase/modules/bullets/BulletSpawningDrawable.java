package com.epicness.alejandria.showcase.modules.bullets;

import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.BULLET_SIZE;
import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.GUN_HEIGHT;
import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.GUN_WIDTH;
import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.MUZZLE_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.SpritePlus;

public class BulletSpawningDrawable implements ModuleDrawable {

    private final SpritePlus gun, bullet, center;

    public BulletSpawningDrawable(Sprite gunSprite, Sprite glowSprite) {
        // Gun
        gun = new SpritePlus(gunSprite);
        gun.setSize(GUN_WIDTH, GUN_HEIGHT);
        gun.setOrigin(GUN_WIDTH * 0.5f, MUZZLE_HEIGHT); // The muzzle is higher than the center
        gun.setOriginBasedPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
        gun.useBilinearFilter();
        // Bullet
        bullet = new SpritePlus(glowSprite);
        bullet.setSize(BULLET_SIZE, BULLET_SIZE);
        bullet.setOriginCenter();
        bullet.setColor(Color.CLEAR);
        // Center
        center = new SpritePlus(glowSprite);
        center.setSize(BULLET_SIZE, BULLET_SIZE);
        center.setOriginCenter();
        center.setOriginBasedPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
        center.setColor(Color.RED);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        gun.draw(spriteBatch);
        bullet.draw(spriteBatch);
        center.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
        gun.drawDebug(shapeDrawer);
        bullet.drawDebug(shapeDrawer);
        center.drawDebug(shapeDrawer);
    }

    public SpritePlus getGun() {
        return gun;
    }

    public SpritePlus getBullet() {
        return bullet;
    }
}