package com.epicness.alejandria.showcase.modules.rendering3d;

import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.epicness.alejandria.showcase.logic.Module;

public class Decal extends Module<DecalModuleDrawable> {

    private FirstPersonCameraController controller;

    public Decal() {
        super("Decal", "Demonstrates rendering of a textured decal\n\nWASD and drag to move");
    }

    @Override
    protected DecalModuleDrawable setup() {
        drawable = new DecalModuleDrawable(sharedAssets.getGlow());
        controller = new FirstPersonCameraController(drawable.getCamera());
        controller.setVelocity(10f);
        return drawable;
    }

    @Override
    public void update(float delta) {
        controller.update(delta);
    }

    @Override
    public void keyDown(int keycode) {
        controller.keyDown(keycode);
    }

    @Override
    public void keyUp(int keycode) {
        controller.keyUp(keycode);
    }

    @Override
    public void touchDragged(float x, float y) {
        controller.touchDragged((int) x, (int) y, 0);
    }
}