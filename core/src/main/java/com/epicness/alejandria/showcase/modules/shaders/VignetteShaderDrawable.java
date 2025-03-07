package com.epicness.alejandria.showcase.modules.shaders;

import static com.badlogic.gdx.graphics.Color.CHARTREUSE;
import static com.badlogic.gdx.graphics.Color.CYAN;
import static com.badlogic.gdx.graphics.Color.ORANGE;
import static com.badlogic.gdx.graphics.Color.PURPLE;
import static com.epicness.alejandria.showcase.constants.ShaderConstants.VIGNETTE_SQUARE_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.SpritePlus;

public class VignetteShaderDrawable implements ModuleDrawable {

    private final SpritePlus square1, square2, square3, square4;

    public VignetteShaderDrawable(Sprite squareSprite) {
        square1 = new SpritePlus(squareSprite);
        square2 = new SpritePlus(squareSprite);
        square3 = new SpritePlus(squareSprite);
        square4 = new SpritePlus(squareSprite);

        square1.setSize(VIGNETTE_SQUARE_SIZE);
        square1.setColor(ORANGE);

        square2.setPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
        square2.setSize(VIGNETTE_SQUARE_SIZE);
        square2.setColor(PURPLE);

        square3.setPosition(0f, VIEWPORT_HALF_HEIGHT);
        square3.setSize(VIGNETTE_SQUARE_SIZE);
        square3.setColor(CHARTREUSE);

        square4.setPosition(VIEWPORT_HALF_WIDTH, 0f);
        square4.setSize(VIGNETTE_SQUARE_SIZE);
        square4.setColor(CYAN);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        square1.draw(spriteBatch);
        square2.draw(spriteBatch);
        square3.draw(spriteBatch);
        square4.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }
}