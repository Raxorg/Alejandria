package com.epicness.alejandria.showcase.modules.rendering3d;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.epicness.alejandria.showcase.logic.Module;

public class DecalExample extends Module<DecalExampleDrawable> {

    private FirstPersonCameraController controller;
    private Decal decal;

    public DecalExample() {
        super("Decal example", "Demonstrates rendering of a textured decal\n\nWASD and drag to move");
    }

    @Override
    protected DecalExampleDrawable setup() {
        drawable = new DecalExampleDrawable(sharedAssets.getGlow());
        controller = new FirstPersonCameraController(drawable.getCamera());
        controller.setVelocity(10f);
        decal = drawable.getDecal();

        return drawable;
    }

    @Override
    public void update(float delta) {
        decal.rotateY(delta * 45f);
        controller.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        drawable.getCamera().viewportWidth = width;
        drawable.getCamera().viewportHeight = height;
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