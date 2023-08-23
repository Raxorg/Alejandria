package com.epicness.alejandria.showcase.modules.shaders;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.logic.Module;

public class VignetteShader extends Module<VignetteShaderModuleDrawable> {

    private ShaderProgram shader;

    public VignetteShader() {
        super("Vignette Shader", "The shader is being applied to the whole screen");
    }

    @Override
    public VignetteShaderModuleDrawable setup() {
        ShaderProgram.pedantic = false;
        renderer.getSpriteBatch().setShader(shader = assets.getVignette());
        return new VignetteShaderModuleDrawable(sharedAssets.getSquare32());
    }

    @Override
    public void update(float delta) {
        shader.bind();
        shader.setUniformf("u_resolution", WINDOW_SIZE, WINDOW_SIZE);
    }

    @Override
    public void exit() {
        renderer.getSpriteBatch().setShader(new SpriteBatch().getShader());
    }
}