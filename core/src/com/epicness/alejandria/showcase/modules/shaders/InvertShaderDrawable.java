package com.epicness.alejandria.showcase.modules.shaders;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;

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

    public InvertShaderDrawable(Sprite weirdShapeSprite, ShaderProgram invertShader) {
        weirdShape = new Sprited(weirdShapeSprite);
        weirdShape.setOriginCenter();
        weirdShape.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        weirdShape.setColor(Color.RED);

        ShaderProgram.pedantic = false;
        normalShader = new SpriteBatch().getShader();
        this.invertShader = invertShader;
        currentShader = invertShader;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        // Let's invert the colors of the whole showcase for fun >:3
        spriteBatch.setShader(currentShader);
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }

    public void toggleShader() {
        currentShader = currentShader == invertShader ? normalShader : invertShader;
    }
}