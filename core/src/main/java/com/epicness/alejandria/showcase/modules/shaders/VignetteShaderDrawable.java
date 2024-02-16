package com.epicness.alejandria.showcase.modules.shaders;

import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;

public class VignetteShaderDrawable implements ModuleDrawable {

    private final Sprited square1, square2, square3, square4;

    public VignetteShaderDrawable(Sprite squareSprite) {
        square1 = new Sprited(squareSprite);
        square2 = new Sprited(squareSprite);
        square3 = new Sprited(squareSprite);
        square4 = new Sprited(squareSprite);

        square1.setSize(CAMERA_HALF_WIDTH);
        square1.setColor(Color.ORANGE);

        square2.setPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        square2.setSize(CAMERA_HALF_WIDTH);
        square2.setColor(Color.PURPLE);

        square3.setPosition(0f, CAMERA_HALF_HEIGHT);
        square3.setSize(CAMERA_HALF_WIDTH);
        square3.setColor(Color.CHARTREUSE);

        square4.setPosition(CAMERA_HALF_WIDTH, 0f);
        square4.setSize(CAMERA_HALF_WIDTH);
        square4.setColor(Color.CYAN);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        square1.draw(spriteBatch);
        square2.draw(spriteBatch);
        square3.draw(spriteBatch);
        square4.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }
}