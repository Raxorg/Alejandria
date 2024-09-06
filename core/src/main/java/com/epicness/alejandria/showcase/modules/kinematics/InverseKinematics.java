package com.epicness.alejandria.showcase.modules.kinematics;

import static com.badlogic.gdx.Input.Keys.R;
import static com.epicness.alejandria.showcase.constants.KinematicsConstants.IK_TENTACLES;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Tentacle;

public class InverseKinematics extends Module<InverseKinematicsDrawable> {

    private Tentacle[] tentacles;

    public InverseKinematics() {
        super("Inverse Kinematics", "Click or touch to toggle tentacle lock\n\nR to reset");
    }

    @Override
    protected InverseKinematicsDrawable setup() {
        drawable = new InverseKinematicsDrawable();
        tentacles = drawable.getTentacles();
        lockInCircle();
        return drawable;
    }

    @Override
    public void mouseMoved(float x, float y) {
        for (int i = 0; i < tentacles.length; i++) {
            tentacles[i].follow(x, y);
        }
    }

    @Override
    public void touchDown(float x, float y, int button) {
        for (int i = 0; i < tentacles.length; i++) {
            tentacles[i].toggleLock();
        }
    }

    @Override
    public void touchDragged(float x, float y) {
        mouseMoved(x, y);
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode != R) return;
        lockInCircle();
    }

    private void lockInCircle() {
        float angleDelta = 360f / IK_TENTACLES;
        for (int i = 0; i < tentacles.length; i++) {
            float x = VIEWPORT_HALF_WIDTH + MathUtils.cosDeg(i * angleDelta) * 150f;
            float y = VIEWPORT_HALF_HEIGHT + MathUtils.sinDeg(i * angleDelta) * 150f;
            tentacles[i].setPosition(x, y);
            tentacles[i].lock();
        }
    }
}