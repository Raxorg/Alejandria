package com.epicness.alejandria.showcase.logic.modules.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.shaders.InvertShaderDrawable;

public class InvertShader extends Module {

    private InvertShaderDrawable drawable;

    @Override
    public void setup() {
        drawable = new InvertShaderDrawable(sharedAssets.getWeirdShape());
        stuff.getShowcase().setDrawable(drawable);

        ShaderProgram.pedantic = false;
        ShaderProgram shader = new ShaderProgram(
                Gdx.files.internal("modules/shaders/shared/vertex.glsl"),
                Gdx.files.internal("modules/shaders/invert.glsl"));
        renderer.getSpriteBatch().setShader(shader);
    }

    @Override
    public void exit() {
        drawable = null;
    }
}