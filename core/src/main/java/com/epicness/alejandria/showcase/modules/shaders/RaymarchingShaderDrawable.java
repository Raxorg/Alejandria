package com.epicness.alejandria.showcase.modules.shaders;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;

public class RaymarchingShaderDrawable implements ModuleDrawable {

    private final ShaderProgram shader;
    private final Sprited canvas;

    public RaymarchingShaderDrawable(Sprite pixel, ShaderProgram raymarchingShader) {
        shader = raymarchingShader;
        ShaderProgram.pedantic = false;
        if (!shader.isCompiled()) {
            Gdx.app.error("SHADER", "Failed to compile shader");
        }
        if (!shader.getLog().isEmpty()) {
            Gdx.app.error("SHADER LOG:", shader.getLog());
        }

        canvas = new Sprited(pixel);
        canvas.setSize(VIEWPORT_WIDTH, SHOWCASE_SIZE);
        canvas.setOriginCenter();
        canvas.setOriginBasedPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.setShader(shader);
        spriteBatch.begin();
        canvas.draw(spriteBatch);
        spriteBatch.end();
        spriteBatch.setShader(null);
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public ShaderProgram getShader() {
        return shader;
    }
}