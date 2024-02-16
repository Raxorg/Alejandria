package com.epicness.fundamentals;

import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.renderer.Renderer;

public class SharedScreen extends ScreenAdapter {

    private Logic logic;
    private Renderer<?> renderer;
    private final OrthographicCamera dynamicCamera, staticCamera;

    public SharedScreen() {
        dynamicCamera = new OrthographicCamera();
        dynamicCamera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        staticCamera = new OrthographicCamera();
        staticCamera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    @Override
    public void render(float delta) {
        logic.update();
        renderer.render();
    }

    @Override
    public void pause() {
        logic.pause();
    }

    public OrthographicCamera getDynamicCamera() {
        return dynamicCamera;
    }

    public OrthographicCamera getStaticCamera() {
        return staticCamera;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public void setRenderer(Renderer<?> renderer) {
        this.renderer = renderer;
    }
}