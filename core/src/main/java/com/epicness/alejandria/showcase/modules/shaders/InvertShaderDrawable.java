package com.epicness.alejandria.showcase.modules.shaders;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;

public class InvertShaderDrawable implements ModuleDrawable {

    private final Sprited weirdShape;
    private final ShaderProgram invertShader, normalShader;
    private ShaderProgram currentShader;

    public InvertShaderDrawable(Sprite weirdShapeSprite, ShaderProgram invertShader) {
        weirdShape = new Sprited(weirdShapeSprite);
        weirdShape.setOriginCenter();
        weirdShape.setOriginBasedPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
        weirdShape.setColor(RED);

        ShaderProgram.pedantic = false;
        normalShader = new SpriteBatch().getShader();
        this.invertShader = invertShader;
        currentShader = invertShader;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        // Let's invert the colors of the whole showcase for fun >:3
        spriteBatch.setShader(currentShader);
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public void toggleShader() {
        currentShader = currentShader == invertShader ? normalShader : invertShader;
    }
}