package com.epicness.alejandria.showcase.stuff.modules.animations;

import static com.epicness.alejandria.showcase.constants.SpriteRotationAnimationConstants.SPRITED_1_SIZE;
import static com.epicness.alejandria.showcase.constants.SpriteRotationAnimationConstants.SPRITED_2_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class SpriteRotationAnimationDrawable implements Drawable {

    private final Sprited pixel, weirdShape;

    public SpriteRotationAnimationDrawable(Sprite pixelSprite, Sprite weirdShapeSprite) {
        pixel = new Sprited(pixelSprite);
        pixel.setSize(SPRITED_1_SIZE);
        pixel.setOriginCenter();
        pixel.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        pixel.setColor(Color.NAVY);

        weirdShape = new Sprited(weirdShapeSprite);
        weirdShape.setSize(SPRITED_2_SIZE);
        weirdShape.setX(CAMERA_HALF_WIDTH - SPRITED_2_SIZE - SPRITED_1_SIZE);
        weirdShape.setY(CAMERA_HALF_HEIGHT - SPRITED_2_SIZE - SPRITED_1_SIZE);
        weirdShape.setOrigin(SPRITED_2_SIZE + SPRITED_1_SIZE, SPRITED_2_SIZE + SPRITED_1_SIZE);
        weirdShape.setColor(Color.BROWN);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        pixel.draw(spriteBatch);
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }

    public Sprited getPixel() {
        return pixel;
    }

    public Sprited getWeirdShape() {
        return weirdShape;
    }
}