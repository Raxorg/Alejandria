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
import com.epicness.fundamentals.stuff.Sprited;

public class BulletSpawningDrawable implements ModuleDrawable {

    private final Sprited gun, bullet, center;

    public BulletSpawningDrawable(Sprite gunSprite, Sprite glowSprite) {
        // Gun
        gun = new Sprited(gunSprite);
        gun.setSize(GUN_WIDTH, GUN_HEIGHT);
        gun.setOrigin(GUN_WIDTH / 2f, MUZZLE_HEIGHT); // The muzzle is higher than the center
        gun.setOriginBasedPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
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
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        gun.drawDebug(shapeRenderer);
        bullet.drawDebug(shapeRenderer);
        center.drawDebug(shapeRenderer);
    }

    public Sprited getGun() {
        return gun;
    }

    public Sprited getBullet() {
        return bullet;
    }
}