package com.epicness.alejandria.showcase.modules;

import static com.epicness.fundamentals.utils.TextUtils.copyOf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.logic.Module;

public class Welcome extends Module<WelcomeDrawable> {

    private ShaderProgram shader;
    private float shaderResolution;
    private float shaderX, shaderY;
    private float time;

    public Welcome() {
        super(
            "Welcome to Alejandria!",
            "Shader based on Kishimisu's tutorial\n\n" +
                "Arrow keys or mouse to navigate\n\n" +
                "\"G\" opens module source on gitHub\n\n" +
                "\"I\" opens module information\n\n" +
                "\"D\" toggles debug mode"
        );
    }

    @Override
    protected WelcomeDrawable setup() {
        drawable = new WelcomeDrawable(copyOf(sharedAssets.getPixelFont()), sharedAssets.getPixel(), assets.getNeonWaves());
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
        float resolution = Math.min(width, height);
        shaderResolution = resolution * 0.7f;
        shaderX = width / 2f - shaderResolution / 2f;
        shaderY = height / 2f - shaderResolution / 2f + resolution * 0.05f;
    }
}