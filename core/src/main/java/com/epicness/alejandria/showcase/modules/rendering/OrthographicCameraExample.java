package com.epicness.alejandria.showcase.modules.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;

public class OrthographicCameraExample extends Module<OrthographicCameraExampleDrawable> {

    private float camX, camY;

    public OrthographicCameraExample() {
        super("Orthographic Camera Example", "The sprite is not moving, the camera is");
    }

    @Override
    public OrthographicCameraExampleDrawable setup() {
        OrthographicCamera camera = screen.getDynamicCamera();
        camX = camera.position.x;
        camY = camera.position.y;
        return new OrthographicCameraExampleDrawable(renderer, sharedAssets.getWeirdShape());
    }

    @Override
    public void update(float delta) {
        float randomX = MathUtils.random(400f, 600f);
        float randomY = MathUtils.random(400f, 600f);
        OrthographicCamera camera = screen.getDynamicCamera();
        camera.position.x = randomX;
        camera.position.y = randomY;
        camera.update();
    }

    @Override
    public void exit() {
        screen.getDynamicCamera().position.set(camX, camY, 0f);
        screen.getDynamicCamera().update();
        renderer.useStaticCamera();
    }
}