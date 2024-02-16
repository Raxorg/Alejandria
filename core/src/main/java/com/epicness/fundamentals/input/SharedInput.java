package com.epicness.fundamentals.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.epicness.fundamentals.SharedScreen;

import java.util.ArrayList;
import java.util.List;

public class SharedInput implements InputProcessor {

    private final List<LogicInputHandler<?, ?, ?, ?>> inputHandlers;
    private OrthographicCamera staticCamera, dynamicCamera;
    private boolean enabled, inputConsumed;
    private final Vector3 unprojected;

    public SharedInput() {
        inputHandlers = new ArrayList<>();
        enabled = false;
        unprojected = new Vector3();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (!enabled) return false;
        inputConsumed = false;
        // Static camera
        staticCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).mouseMoved(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }
        // Dynamic camera
        dynamicCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0, n = inputHandlers.size(); i < n; i++) {
            inputHandlers.get(i).mouseMovedDynamic(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (!enabled) return false;
        inputConsumed = false;

        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).scrolled(amountX, amountY);
            if (inputConsumed) return true;
        }

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (pointer != 0 || !enabled) return false;
        inputConsumed = false;
        // Static camera
        staticCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).touchDown(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }
        // Dynamic camera
        dynamicCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).touchDownDynamic(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer != 0 || !enabled) return false;
        inputConsumed = false;
        // Static camera
        staticCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).touchDragged(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }
        // Dynamic camera
        dynamicCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).touchDraggedDynamic(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer != 0 || !enabled) return false;
        inputConsumed = false;
        // Static camera
        staticCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).touchUp(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }
        // Dynamic camera
        dynamicCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).touchUpDynamic(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }

        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        if (pointer != 0 || !enabled) return false;
        inputConsumed = false;
        // Static camera
        staticCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).touchCancelled(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }
        // Dynamic camera
        dynamicCamera.unproject(unprojected.set(screenX, screenY, 0f));
        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).touchCancelledDynamic(unprojected.x, unprojected.y);
            if (inputConsumed) return true;
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!enabled) return false;
        inputConsumed = false;

        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).keyDown(keycode);
            if (inputConsumed) return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (!enabled) return false;
        inputConsumed = false;

        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).keyUp(keycode);
            if (inputConsumed) return true;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (!enabled) return false;
        inputConsumed = false;

        for (int i = 0; i < inputHandlers.size(); i++) {
            inputHandlers.get(i).keyTyped(character);
            if (inputConsumed) return true;
        }

        return false;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void clearInputHandlers() {
        inputHandlers.clear();
    }

    public void addInputHandler(LogicInputHandler<?, ?, ?, ?> inputHandler) {
        inputHandlers.add(inputHandler);
    }

    public void removeInputHandler(LogicInputHandler<?, ?, ?, ?> inputHandler) {
        inputHandlers.remove(inputHandler);
    }

    public void consumeInput() {
        inputConsumed = true;
    }

    // Structure
    public void setScreen(SharedScreen screen) {
        staticCamera = screen.getStaticCamera();
        dynamicCamera = screen.getDynamicCamera();
    }
}