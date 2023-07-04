package com.epicness.standalone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class GlowShaderTest extends ApplicationAdapter {

    private SpriteBatch batch;
    private ShaderProgram shader;
    private Sprite sprite;
    private float time;

    @Override
    public void create() {
        batch = new SpriteBatch();
        FileHandle vertexShader = Gdx.files.internal("alejandria/showcase/shaders/shared/vertex.glsl");
        FileHandle fragmentShader = Gdx.files.internal("glowTest.glsl");
        //init shaders
        shader = new ShaderProgram(vertexShader, fragmentShader);
        shader.bind();
        ShaderProgram.pedantic = false;
        if (!shader.isCompiled()) {
            System.out.println("ERROR: Failed to compile shaders");
        }
        if (!shader.getLog().isEmpty()) {
            System.out.println("SHADER LOG: " + shader.getLog());
        }
        Texture tex = new Texture("fundamentals/images/pixel.png");
        sprite = new Sprite(tex);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render() {
        time += Gdx.graphics.getDeltaTime()*5f;
        //rendering
        batch.setShader(shader);
        shader.setUniformf("time", time);
        //drawing a sprite on which the shader will be generated
        batch.begin();
        sprite.draw(batch);
        batch.end();
        batch.setShader(null);
    }
}