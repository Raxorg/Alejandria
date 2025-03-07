package com.epicness.alejandria.showcase.modules.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.logic.Module;

public class VignetteShader extends Module<VignetteShaderDrawable> {

    private ShaderProgram shader;
    private float shaderResolution;
    private float shaderX, shaderY;

    public VignetteShader() {
        super("Vignette Shader", "The shader is being applied to the whole screen");
    }

    @Override
    public VignetteShaderDrawable setup() {
        ShaderProgram.pedantic = false;
        renderer.getSpriteBatch().setShader(shader = assets.getVignette());
        updateShaderValues(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return new VignetteShaderDrawable(sharedAssets.getSquare32());
    }

    @Override
    public void update(float delta) {
        shader.bind();
        shader.setUniformf("u_resolution", shaderResolution, shaderResolution);
        shader.setUniformf("u_position", shaderX, shaderY);
    }

    @Override
    public void resize(int width, int height) {
        updateShaderValues(width, height);
    }

    private void updateShaderValues(int width, int height) {
        shaderResolution = Math.min(width, height);
        shaderX = width * 0.5f - shaderResolution * 0.5f;
        shaderY = height * 0.5f - shaderResolution * 0.5f;
    }

    @Override
    public void exit() {
        renderer.getSpriteBatch().setShader(new SpriteBatch().getShader());
    }
}