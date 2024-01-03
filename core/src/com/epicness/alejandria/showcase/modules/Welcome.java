package com.epicness.alejandria.showcase.modules;

import static com.epicness.alejandria.showcase.constants.WelcomeConstants.SHADER_POSITION;
import static com.epicness.alejandria.showcase.constants.WelcomeConstants.SHADER_RESOLUTION;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.logic.Module;

public class Welcome extends Module<WelcomeDrawable> {

    private ShaderProgram shader;
    private float time;

    public Welcome() {
        super(
            "Welcome to Alejandria!",
            "Shader can also be found in standalone package\n\n" +
                "Arrow keys or mouse to navigate\n\n" +
                "G opens module source on gitHub\n\n" +
                "I opens module information\n\n" +
                "D toggles debug mode"
        );
    }

    @Override
    protected WelcomeDrawable setup() {
        drawable = new WelcomeDrawable(assets.getPixelFont(), sharedAssets.getPixel(), assets.getNeonWaves());
        shader = drawable.getShader();
        return drawable;
    }

    @Override
    public void update(float delta) {
        time += delta;

        shader.bind();
        shader.setUniformf("u_resolution", SHADER_RESOLUTION, SHADER_RESOLUTION);
        shader.setUniformf("u_position", SHADER_POSITION, SHADER_POSITION);
        shader.setUniformf("time", time);
    }
}