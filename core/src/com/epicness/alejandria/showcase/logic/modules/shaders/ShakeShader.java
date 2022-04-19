package com.epicness.alejandria.showcase.logic.modules.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.modules.Module;

public class ShakeShader extends Module {

    private ShaderProgram shader;

    public ShakeShader() {
        super("Shake Shader");
    }

    @Override
    public void setup() {
        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(
                Gdx.files.internal("modules/shaders/shake.glsl"),
                Gdx.files.internal("modules/shaders/shared/fragment.glsl"));
        renderer.getSpriteBatch().setShader(shader);
    }

    @Override
    public void update(float delta) {
        float shakeStrength = 10f;
        float shakeX = MathUtils.random() * shakeStrength - shakeStrength / 2f;
        float shakeY = MathUtils.random() * shakeStrength - shakeStrength / 2f;
        shader.setUniformf("u_distort", shakeX, shakeY, 0f, 0f);
    }
}