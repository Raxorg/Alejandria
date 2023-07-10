package com.epicness.alejandria.showcase.modules.kinematics;

import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.shapes.Tentacle;

public class InverseKinematics extends Module<InverseKinematicsDrawable> {

    public InverseKinematics() {
        super("Inverse Kinematics", "Click or touch to toggle tentacle lock");
    }

    @Override
    protected InverseKinematicsDrawable setup() {
        drawable = new InverseKinematicsDrawable(renderer.getSpriteBatch(), sharedAssets.getPixel());
        Tentacle[] tentacles = drawable.getTentacles();
        for (int i = 0; i < tentacles.length; i++) {
            tentacles[i].lock();
        }
        return drawable;
    }

    @Override
    public void mouseMoved(float x, float y) {
        Tentacle[] tentacles = drawable.getTentacles();
        for (int i = 0; i < tentacles.length; i++) {
            tentacles[i].follow(x, y);
        }
    }

    @Override
    public void touchDown(float x, float y) {
        Tentacle[] tentacles = drawable.getTentacles();
        for (int i = 0; i < tentacles.length; i++) {
            tentacles[i].toggleLock();
        }
    }

    @Override
    public void touchDragged(float x, float y) {
        mouseMoved(x, y);
    }
}