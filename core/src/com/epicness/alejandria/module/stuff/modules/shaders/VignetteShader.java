package com.epicness.alejandria.module.stuff.modules.shaders;

import static com.epicness.alejandria.AssetPaths.VERTEX_SHADER_PATH;
import static com.epicness.alejandria.AssetPaths.VIGNETTE_SHADER_PATH;
import static com.epicness.alejandria.ModuleID.VIGNETTE_SHADER;
import static com.epicness.fundamentals.SharedConstants.SQUARE_32_PATH;
import static com.epicness.fundamentals.SharedConstants.WEIRD_SHAPE_PATH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.module.stuff.modules.Module;
import com.epicness.fundamentals.stuff.Sprited;

public class VignetteShader extends Module {

    private SpriteBatch spriteBatch;
    private Sprited sprited1, sprited2, sprited3;
    private ShaderProgram shader;

    public VignetteShader() {
        super(VIGNETTE_SHADER);
    }

    @Override
    public void setup() {
        spriteBatch = new SpriteBatch();

        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(Gdx.files.internal(VERTEX_SHADER_PATH), Gdx.files.internal(VIGNETTE_SHADER_PATH));
        spriteBatch.setShader(shader);

        System.out.println(shader.isCompiled());
        System.out.println(shader.getLog());

        sprited1 = new Sprited(new Sprite(new Texture(Gdx.files.internal(SQUARE_32_PATH))));
        sprited1.setSize(400f, 400f);
        sprited1.setColor(Color.ORANGE);

        sprited2 = new Sprited(new Sprite(new Texture(Gdx.files.internal(WEIRD_SHAPE_PATH))));
        sprited2.setPosition(400f, 400f);
        sprited2.setSize(400f, 400f);
        sprited2.setColor(Color.PURPLE);

        sprited3 = new Sprited(new Sprite(new Texture(Gdx.files.internal(SQUARE_32_PATH))));
        sprited3.setPosition(0f, 400f);
        sprited3.setSize(400f, 400f);
        sprited3.setColor(Color.CHARTREUSE);
    }

    @Override
    public void update(float delta) {
        // If not set each frame, use shader.bind();
        shader.setUniformf("u_resolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void draw() {
        ScreenUtils.clear(Color.RED);
        spriteBatch.begin();
        sprited1.draw(spriteBatch);
        sprited2.draw(spriteBatch);
        sprited3.draw(spriteBatch);
        spriteBatch.end();
    }
}