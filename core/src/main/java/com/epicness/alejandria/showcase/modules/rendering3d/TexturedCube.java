package com.epicness.alejandria.showcase.modules.rendering3d;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.math.Vector3;
import com.epicness.alejandria.showcase.logic.Module;

public class TexturedCube extends Module<TexturedCubeDrawable> {

    private FirstPersonCameraController controller;
    private ModelInstance cube;

    public TexturedCube() {
        super(
            "Textured cube",
            "Demonstrates rendering of a textured cube in 3D space\n\nWASD and drag to move"
        );
    }

    @Override
    protected TexturedCubeDrawable setup() {
        drawable = new TexturedCubeDrawable(sharedAssets.getWeirdShape());
        controller = new FirstPersonCameraController(drawable.getCamera());
        controller.setVelocity(10f);
        cube = drawable.getModelInstance();

        return drawable;
    }

    @Override
    public void update(float delta) {
        cube.transform.rotate(Vector3.Y, delta * 45f);
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