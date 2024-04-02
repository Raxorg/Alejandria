package com.epicness.alejandria.showcase.modules.patterns;

import com.epicness.alejandria.showcase.logic.Module;

public class DragonCurve extends Module<DragonCurveDrawable> {

    private float time;

    public DragonCurve() {
        super("Dragon Curve", "The dragon curve pattern consists of a single line!\n\nClick to restart");
    }

    @Override
    protected DragonCurveDrawable setup() {
        return new DragonCurveDrawable();
    }

    @Override
    public void update(float delta) {
        time += delta;
        if (time >= 0.005f) {
            drawable.setOffset(Math.max(0, drawable.getOffset() - 2));
            time = 0f;
        }
    }

    @Override
    public void touchDown(float x, float y, int button) {
        drawable.setOffset(drawable.getDragonCurve().length);
    }
}