package com.epicness.alejandria.showcase.logic.modules.shaders;

import static com.epicness.alejandria.AssetPaths.VERTEX_SHADER_PATH;
import static com.epicness.alejandria.AssetPaths.VIGNETTE_SHADER_PATH;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.shaders.VignetteShaderDrawable;

// TODO: 29/4/2022 Add a message to point how the shader is applied twice
public class VignetteShader extends Module {

    private ShaderProgram shader;

    public VignetteShader() {
        super("Vignette Shader");
    }

    @Override
    public Drawable setup() {
        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(Gdx.files.internal(VERTEX_SHADER_PATH), Gdx.files.internal(VIGNETTE_SHADER_PATH));
        renderer.getSpriteBatch().setShader(shader);

        return new VignetteShaderDrawable(sharedAssets.getSquare());
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