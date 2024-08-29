package com.epicness.alejandria.showcase.modules.shaders;

import static com.epicness.alejandria.showcase.constants.RaymarchingConstants.CANVAS_HEIGHT;
import static com.epicness.alejandria.showcase.constants.RaymarchingConstants.CANVAS_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;

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
        canvas.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setOriginCenter();
        canvas.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
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