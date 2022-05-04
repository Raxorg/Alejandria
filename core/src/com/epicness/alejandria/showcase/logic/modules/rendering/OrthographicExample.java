package com.epicness.alejandria.showcase.logic.modules.rendering;

import static com.epicness.fundamentals.SharedConstants.CENTER_X;
import static com.epicness.fundamentals.SharedConstants.CENTER_Y;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.rendering.OrthographicExampleDrawable;

public class OrthographicExample extends Module {

    private OrthographicExampleDrawable drawable;

    public OrthographicExample() {
        super("Orthographic Camera Example", "The sprite is not moving, the camera is");
    }

    @Override
    public Drawable setup() {
        drawable = new OrthographicExampleDrawable(renderer, sharedAssets.getWeirdShape());
        return drawable;
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
        screen.getDynamicCamera().position.set(CENTER_X, CENTER_Y, 0f);
        screen.getDynamicCamera().update();
        renderer.useStaticCamera();
        drawable = null;
    }
}