package com.epicness.alejandria.showcase.modules.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.logic.Module;

public class InvertShader extends Module<InvertShaderDrawable> {

    public InvertShader() {
        super(
            "Invert Shader",
            "This shader inverts all the colors except the screen clear color\n\nSpace to toggle"
        );
    }

    @Override
    public InvertShaderDrawable setup() {
        return new InvertShaderDrawable(sharedAssets.getWeirdShape(), assets.getInvert());
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