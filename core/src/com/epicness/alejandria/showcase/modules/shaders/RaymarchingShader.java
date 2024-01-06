package com.epicness.alejandria.showcase.modules.shaders;

import static com.epicness.alejandria.showcase.constants.RaymarchingConstants.SHADER_POSITION;
import static com.epicness.alejandria.showcase.constants.RaymarchingConstants.SHADER_RESOLUTION;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.logic.Module;

public class RaymarchingShader extends Module<RaymarchingShaderDrawable> {

    private ShaderProgram shader;
    private float time;

    public RaymarchingShader() {
        super("Raymarching shader", "Based on Kishimisu's tutorial");
    }

    @Override
    protected RaymarchingShaderDrawable setup() {
        drawable = new RaymarchingShaderDrawable(sharedAssets.getPixel(), assets.getRaymarching());
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