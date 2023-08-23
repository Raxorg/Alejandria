package com.epicness.alejandria.showcase.modules.rendering;

import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;

public class OrthographicExample extends Module<OrthographicExampleModuleDrawable> {

    public OrthographicExample() {
        super("Orthographic Camera Example", "The sprite is not moving, the camera is");
    }

    @Override
    public OrthographicExampleModuleDrawable setup() {
        return new OrthographicExampleModuleDrawable(renderer, sharedAssets.getWeirdShape());
    }

    @Override
    public void update(float delta) {
        float randomX = MathUtils.random(400f, 600f);
        float randomY = MathUtils.random(400f, 600f);
        OrthographicCamera camera = screen.getDynamicCamera();
        camera.translate(randomX, randomY);
        camera.position.x = randomX;
        camera.position.y = randomY;
        camera.update();
    }

    @Override
    public void exit() {
        screen.getDynamicCamera().position.set(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT, 0f);
        screen.getDynamicCamera().update();
        renderer.useStaticCamera();
    }
}