package com.epicness.alejandria.showcase.logic.input;

import com.epicness.alejandria.showcase.logic.modules.bullets.BulletSpawning;

public class BulletSpawningInput extends ModuleInput<BulletSpawning> {

    @Override
    protected Class<BulletSpawning> setup() {
        return BulletSpawning.class;
    }

    @Override
    public void mouseMoved(float x, float y) {
        module.mouseMoved(x, y);
    }

    @Override
    public void touchDown(float x, float y) {
        module.touchDown();
    }
}