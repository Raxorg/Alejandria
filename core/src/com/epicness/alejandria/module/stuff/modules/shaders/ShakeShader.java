package com.epicness.alejandria.module.stuff.modules.shaders;

import static com.epicness.alejandria.ModuleID.SHAKE_SHADER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.module.stuff.modules.Module;
import com.epicness.fundamentals.stuff.Sprited;

public class ShakeShader extends Module {

    private SpriteBatch spriteBatch;
    private ShaderProgram shader;
    private Sprited weirdShape;

    public ShakeShader() {
        super(SHAKE_SHADER);
    }

    @Override
    public void setup() {
        spriteBatch = new SpriteBatch();
        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(
                Gdx.files.internal("modules/shaders/shake.glsl"),
                Gdx.files.internal("modules/shaders/shared/fragment.glsl"));
        spriteBatch.setShader(shader);
        System.out.println(shader.isCompiled());
        weirdShape = new Sprited(new Sprite(new Texture(Gdx.files.internal("fundamentals/images/weirdShape.png"))));
        float x = Gdx.graphics.getWidth() / 2f - weirdShape.getWidth() / 2f;
        float y = Gdx.graphics.getHeight() / 2f - weirdShape.getHeight() / 2f;
        weirdShape.setPosition(x, y);
        weirdShape.setColor(Color.ORANGE);
    }

    @Override
    public void update(float delta) {
        float shakeStrength = 10f;
        float shakeX = MathUtils.random() * shakeStrength - shakeStrength / 2f;
        float shakeY = MathUtils.random() * shakeStrength - shakeStrength / 2f;
        shader.setUniformf("u_distort", shakeX, shakeY, 0f, 0f);
    }

    @Override
    public void draw() {
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }
}