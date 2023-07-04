package com.epicness.alejandria.showcase.modules.animations;

import static com.epicness.alejandria.showcase.constants.SpriteRotationAnimationConstants.SQUARE_SIZE;
import static com.epicness.alejandria.showcase.constants.SpriteRotationAnimationConstants.WEIRD_SHAPE_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;

public class SpriteRotationAnimationDrawable implements Drawable {

    private final Sprite pixel, weirdShape;

    public SpriteRotationAnimationDrawable(Sprite pixelSprite, Sprite weirdShapeSprite) {
        pixel = new Sprite(pixelSprite);
        pixel.setSize(SQUARE_SIZE, SQUARE_SIZE);
        pixel.setOriginCenter();
        pixel.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        pixel.setColor(Color.NAVY);

        weirdShape = new Sprite(weirdShapeSprite);
        weirdShape.setSize(WEIRD_SHAPE_SIZE, WEIRD_SHAPE_SIZE);
        weirdShape.setX(CAMERA_HALF_WIDTH - WEIRD_SHAPE_SIZE - SQUARE_SIZE);
        weirdShape.setY(CAMERA_HALF_HEIGHT - WEIRD_SHAPE_SIZE - SQUARE_SIZE);
        weirdShape.setOrigin(WEIRD_SHAPE_SIZE + SQUARE_SIZE, WEIRD_SHAPE_SIZE + SQUARE_SIZE);
        weirdShape.setColor(Color.BROWN);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        pixel.draw(spriteBatch);
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }

    public Sprite getPixel() {
        return pixel;
    }

    public Sprite getWeirdShape() {
        return weirdShape;
    }
}