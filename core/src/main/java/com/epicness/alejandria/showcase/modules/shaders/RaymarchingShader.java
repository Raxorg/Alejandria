package com.epicness.alejandria.showcase.modules.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.logic.Module;

public class RaymarchingShader extends Module<RaymarchingShaderDrawable> {

    private ShaderProgram shader;
    private float shaderResolution;
    private float shaderX, shaderY;
    private float time;

    public RaymarchingShader() {
        super("Raymarching shader", "Based on Kishimisu's tutorial");
    }

    @Override
    protected RaymarchingShaderDrawable setup() {
        drawable = new RaymarchingShaderDrawable(sharedAssets.getPixel(), assets.getRaymarching());
        shader = drawable.getShader();
        updateShaderValues(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return drawable;
    }

    @Override
    public void update(float delta) {
        time += delta;

        shader.bind();
        shader.setUniformf("u_resolution", shaderResolution, shaderResolution);
        shader.setUniformf("u_position", shaderX, shaderY);
        shader.setUniformf("time", time);
    }

    @Override
    public void resize(int width, int height) {
        updateShaderValues(width, height);
    }

    private void updateShaderValues(int width, int height) {
        shaderResolution = Math.min(width, height);
        shaderX = width / 2f - shaderResolution / 2f;
        shaderY = height / 2f - shaderResolution / 2f;
    }
}