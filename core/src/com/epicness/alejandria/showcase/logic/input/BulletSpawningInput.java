package com.epicness.alejandria.showcase.logic.input;

import com.epicness.alejandria.showcase.logic.modules.bullets.BulletSpawning;

public class BulletSpawningInput extends ModuleInput {

    @Override
    public void mouseMoved(float x, float y) {
        BulletSpawning bulletSpawning = (BulletSpawning) logic.getHandler(BulletSpawning.class);
        bulletSpawning.mouseMoved(x, y);
    }

    @Override
    public void touchDown(float x, float y) {
        BulletSpawning bulletSpawning = (BulletSpawning) logic.getHandler(BulletSpawning.class);
        bulletSpawning.touchDown();
    }
}