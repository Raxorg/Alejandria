package com.epicness.alejandria.showcase.modules;

import static com.epicness.alejandria.showcase.constants.WelcomeConstants.SHADER_RESOLUTION;
import static com.epicness.alejandria.showcase.constants.WelcomeConstants.SHADER_X;
import static com.epicness.alejandria.showcase.constants.WelcomeConstants.SHADER_Y;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.logic.Module;

public class Welcome extends Module<WelcomeDrawable> {

    private ShaderProgram shader;
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
        drawable = new WelcomeDrawable(sharedAssets.getPixelFont(), sharedAssets.getPixel(), assets.getNeonWaves());
        shader = drawable.getShader();
        return drawable;
    }

    @Override
    public void update(float delta) {
        time += delta;

        shader.bind();
        shader.setUniformf("u_resolution", SHADER_RESOLUTION, SHADER_RESOLUTION);
        shader.setUniformf("u_position", SHADER_X, SHADER_Y);
        shader.setUniformf("time", time);
    }
}