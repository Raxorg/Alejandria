package com.epicness.alejandria.showcase.logic.modules.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.shaders.InvertShaderDrawable;

public class InvertShader extends Module<InvertShaderDrawable> {

    public InvertShader() {
        super(
                "Invert Shader",
                "This shader inverts the colors of everything notice how the background stays black"
        );
    }

    @Override
    public InvertShaderDrawable setup() {
        return new InvertShaderDrawable(sharedAssets.getWeirdShape());
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            drawable.toggleShader();
        }
    }

    @Override
    public void exit() {
        renderer.getSpriteBatch().setShader(new SpriteBatch().getShader());
    }
}