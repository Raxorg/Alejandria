package com.epicness.alejandria.showcase.stuff.modules.shaders;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class InvertShaderDrawable implements Drawable {

    private final Sprited weirdShape;
    private final ShaderProgram invertShader, normalShader;
    private ShaderProgram currentShader;

    public InvertShaderDrawable(Sprite weirdShapeSprite) {
        weirdShape = new Sprited(weirdShapeSprite);
        weirdShape.setOriginCenter();
        weirdShape.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        weirdShape.setColor(Color.RED);

        ShaderProgram.pedantic = false;
        normalShader = new SpriteBatch().getShader();
        invertShader = new ShaderProgram(
                Gdx.files.internal("modules/shaders/shared/vertex.glsl"),
                Gdx.files.internal("modules/shaders/invert.glsl"));
        currentShader = invertShader;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        // Let's change the whole showcase shader for fun >:3 for that we will change the
        // shader at the end, and use the default shader at the start so we don't invert the
        // colors twice (happens because we're using a framebuffer to render the modules)
        spriteBatch.setShader(normalShader);

        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();

        spriteBatch.setShader(currentShader);
    }

    public void toggleShader() {
        currentShader = currentShader == invertShader ? normalShader : invertShader;
    }
}