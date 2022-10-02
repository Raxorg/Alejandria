package com.epicness.alejandria.showcase.logic.modules.shaders;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.shaders.ShakeShaderDrawable;

public class ShakeShader extends Module<ShakeShaderDrawable> {

    private ShaderProgram shader;

    public ShakeShader() {
        super("Shake Shader", "The sprites are not moving, it is the effect of the shader");
    }

    @Override
    public ShakeShaderDrawable setup() {
        Gdx.app.setLogLevel(Application.LOG_ERROR);
        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(
                Gdx.files.internal("modules/shaders/shake.glsl"),
                Gdx.files.internal("modules/shaders/shared/fragment.glsl"));
        if (!shader.isCompiled()) {
            Gdx.app.error("SHADER", shader.getLog());
        }
        renderer.getSpriteBatch().setShader(shader);

        return new ShakeShaderDrawable(sharedAssets.getWeirdShape());
    }

    @Override
    public void update(float delta) {
        float shakeStrength = 10f;
        float shakeX = MathUtils.random() * shakeStrength - shakeStrength / 2f;
        float shakeY = MathUtils.random() * shakeStrength - shakeStrength / 2f;
        shader.setUniformf("u_distort", shakeX, shakeY, 0f, 0f);
    }

    @Override
    public void exit() {
        renderer.getSpriteBatch().setShader(new SpriteBatch().getShader());
    }
}