package com.epicness.alejandria.showcase.stuff.modules.shaders;

import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.INVERT_SHADER_PATH;
import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.VERTEX_SHADER_PATH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;

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
                Gdx.files.internal(VERTEX_SHADER_PATH),
                Gdx.files.internal(INVERT_SHADER_PATH));
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