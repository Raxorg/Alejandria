package com.epicness.alejandria.showcase.stuff.modules.bullets;

import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.BULLET_SIZE;
import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.GUN_HEIGHT;
import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.GUN_WIDTH;
import static com.epicness.alejandria.showcase.constants.BulletSpawningConstants.MUZZLE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.CENTER_X;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.CENTER_Y;
import static com.epicness.fundamentals.SharedConstants.GRASS;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class BulletSpawningDrawable implements Drawable {

    private final Sprited gun, bullet, center;

    public BulletSpawningDrawable(Sprite gunSprite, Sprite glowSprite) {
        // Gun
        gun = new Sprited(gunSprite);
        gun.setSize(GUN_WIDTH, GUN_HEIGHT);
        gun.setOrigin(GUN_WIDTH / 2f, MUZZLE_HEIGHT); // The muzzle is higher than the center
        gun.setOriginBasedPosition(CENTER_X, CENTER_Y);
        // Bullet
        bullet = new Sprited(glowSprite);
        bullet.setSize(BULLET_SIZE, BULLET_SIZE);
        bullet.setOriginCenter();
        // Center
        center = new Sprited(glowSprite);
        center.setSize(BULLET_SIZE, BULLET_SIZE);
        center.setOriginCenter();
        center.setOriginBasedPosition(CENTER_X, CENTER_Y);
        center.setColor(Color.RED);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        ScreenUtils.clear(GRASS);
        spriteBatch.begin();
        gun.draw(spriteBatch);
        bullet.draw(spriteBatch);
        center.draw(spriteBatch);
        spriteBatch.end();
    }

    public Sprited getGun() {
        return gun;
    }

    public Sprited getBullet() {
        return bullet;
    }
}