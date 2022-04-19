package com.epicness.alejandria.showcase.stuff.modules.bullets;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.GRASS;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class BulletSpawningDrawable implements Drawable {

    private final Sprited gun, bullet;

    public BulletSpawningDrawable(Sprite gunSprite, Sprite glowSprite) {
        // Gun
        gun = new Sprited(gunSprite);
        gun.setOriginCenter();
        gun.setOriginBasedPosition(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
        gun.setOrigin(gun.getWidth() / 2f, gun.getHeight() * 0.85f); // Gun end is higher than the center
        // Bullet
        bullet = new Sprited(glowSprite);
        bullet.setSize(gun.getHeight() / 5f, gun.getHeight() / 5f);
        bullet.setOriginCenter();
        bullet.setColor(Color.RED);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        ScreenUtils.clear(GRASS);
        spriteBatch.begin();
        gun.draw(spriteBatch);
        bullet.draw(spriteBatch);
        spriteBatch.end();
    }

    public Sprited getGun() {
        return gun;
    }

    public Sprited getBullet() {
        return bullet;
    }
}