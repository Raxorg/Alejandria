package com.epicness.alejandria.showcase.modules.animations;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.alejandria.showcase.constants.SpriteRotationAnimationConstants.SQUARE_SIZE;
import static com.epicness.alejandria.showcase.constants.SpriteRotationAnimationConstants.WEIRD_SHAPE_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class SpriteRotationAnimationDrawable implements ModuleDrawable {

    private final Sprite pixel, weirdShape;

    public SpriteRotationAnimationDrawable(Sprite pixelSprite, Sprite weirdShapeSprite) {
        pixel = new Sprite(pixelSprite);
        pixel.setSize(SQUARE_SIZE, SQUARE_SIZE);
        pixel.setOriginCenter();
        pixel.setOriginBasedPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
        pixel.setColor(BLUE);

        weirdShape = new Sprite(weirdShapeSprite);
        weirdShape.setSize(WEIRD_SHAPE_SIZE, WEIRD_SHAPE_SIZE);
        weirdShape.setX(VIEWPORT_HALF_WIDTH - WEIRD_SHAPE_SIZE - SQUARE_SIZE);
        weirdShape.setY(VIEWPORT_HALF_HEIGHT - WEIRD_SHAPE_SIZE - SQUARE_SIZE);
        weirdShape.setOrigin(WEIRD_SHAPE_SIZE + SQUARE_SIZE, WEIRD_SHAPE_SIZE + SQUARE_SIZE);
        weirdShape.setColor(RED);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        pixel.draw(spriteBatch);
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        shapeRenderer.rect(pixel.getBoundingRectangle());
        shapeRenderer.rect(weirdShape.getBoundingRectangle());
    }

    public Sprite getPixel() {
        return pixel;
    }

    public Sprite getWeirdShape() {
        return weirdShape;
    }
}