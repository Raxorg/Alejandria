package com.epicness.alejandria.module.modules.shaders;

import static com.epicness.alejandria.ModuleID.INVERT_SHADER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.module.modules.Module;
import com.epicness.fundamentals.stuff.Sprited;

public class InvertShader extends Module {

    private SpriteBatch spriteBatch;
    private Sprited weirdShape;

    public InvertShader() {
        super(INVERT_SHADER);
    }

    @Override
    public void setup() {
        spriteBatch = new SpriteBatch();
        ShaderProgram.pedantic = false;
        ShaderProgram shader = new ShaderProgram(
                Gdx.files.internal("modules/shaders/shared/vertex.glsl"),
                Gdx.files.internal("modules/shaders/invert.glsl"));
        spriteBatch.setShader(shader);
        System.out.println(shader.isCompiled());
        weirdShape = new Sprited(new Sprite(new Texture(Gdx.files.internal("fundamentals/images/weirdShape.png"))));
        weirdShape.setColor(Color.RED);
    }

    @Override
    public void draw() {
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }
}