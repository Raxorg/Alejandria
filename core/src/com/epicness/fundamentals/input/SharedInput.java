package com.epicness.fundamentals.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class SharedInput extends InputAdapter {

    private InputHandler inputHandler;
    private OrthographicCamera camera;
    private boolean enabled;
    private Vector3 unprojected;

    public SharedInput() {
        enabled = false;
        unprojected = new Vector3();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (pointer != 0 || !enabled || inputHandler == null) {
            return false;
        }
        unprojected = camera.unproject(new Vector3(screenX, screenY, 0f));
        inputHandler.touchDown(unprojected.x, unprojected.y);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer != 0 || !enabled || inputHandler == null) {
            return false;
        }
        unprojected = camera.unproject(new Vector3(screenX, screenY, 0f));
        inputHandler.touchDragged(unprojected.x, unprojected.y);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer != 0 || !enabled || inputHandler == null) {
            return false;
        }
        unprojected = camera.unproject(new Vector3(screenX, screenY, 0f));
        inputHandler.touchUp(unprojected.x, unprojected.y);
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!enabled || inputHandler == null) {
            return false;
        }
        inputHandler.keyDown(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (!enabled || inputHandler == null) {
            return false;
        }
        inputHandler.keyUp(keycode);
        return true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // Structure
    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}