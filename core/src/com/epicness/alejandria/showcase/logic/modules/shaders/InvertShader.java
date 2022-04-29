package com.epicness.alejandria.showcase.logic.modules.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        return drawable = new InvertShaderDrawable(sharedAssets.getWeirdShape());
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            drawable.toggleShader();
        }
    }
}