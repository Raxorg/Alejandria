package com.epicness.alejandria.showcase.logic.modules.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.shaders.InvertShaderDrawable;

public class InvertShader extends Module {

    private InvertShaderDrawable drawable;

    public InvertShader() {
        super("Invert Shader");
    }

    @Override
    public Drawable setup() {
        ShaderProgram.pedantic = false;
        ShaderProgram shader = new ShaderProgram(
                Gdx.files.internal("modules/shaders/shared/vertex.glsl"),
                Gdx.files.internal("modules/shaders/invert.glsl"));
        renderer.getSpriteBatch().setShader(shader);

        return drawable = new InvertShaderDrawable(sharedAssets.getWeirdShape());
    }

    @Override
    public void exit() {
        drawable = null;
    }
}