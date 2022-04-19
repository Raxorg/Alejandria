package com.epicness.alejandria.showcase.logic.modules.shaders;

import static com.epicness.alejandria.AssetPaths.VERTEX_SHADER_PATH;
import static com.epicness.alejandria.AssetPaths.VIGNETTE_SHADER_PATH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.shaders.VignetteShaderDrawable;

public class VignetteShader extends Module {

    private VignetteShaderDrawable drawable;
    private ShaderProgram shader;

    public VignetteShader() {
        super("Vignette Shader");
    }

    @Override
    public Drawable setup() {
        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(Gdx.files.internal(VERTEX_SHADER_PATH), Gdx.files.internal(VIGNETTE_SHADER_PATH));
        renderer.getSpriteBatch().setShader(shader);

        return drawable = new VignetteShaderDrawable(
                sharedAssets.getSquare(),
                sharedAssets.getWeirdShape(),
                sharedAssets.getSquare()
        );
    }

    @Override
    public void update(float delta) {
        // If not set each frame, use shader.bind();
        shader.setUniformf("u_resolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void exit() {
        drawable = null;
    }
}