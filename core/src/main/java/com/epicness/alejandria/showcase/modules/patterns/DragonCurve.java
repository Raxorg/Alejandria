package com.epicness.alejandria.showcase.modules.patterns;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.Color.WHITE;

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

            float portion = (float) drawable.getOffset() / drawable.getDragonCurve().length;
            renderer.getShapeDrawer().setColor(RED.cpy().lerp(BLUE, portion));
        }
    }

    @Override
    public void touchDown(float x, float y, int button) {
        drawable.setOffset(drawable.getDragonCurve().length);
    }

    @Override
    protected void exit() {
        renderer.getShapeDrawer().setColor(WHITE);
    }
}