package com.epicness.alejandria.showcase.stuff.modules.rendering;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.renderer.Renderer;

public class OrthographicExampleDrawable implements Drawable {

    private final Renderer<?> renderer;
    private final Sprite weirdShape;

    public OrthographicExampleDrawable(Renderer<?> renderer, Sprite weirdShapeSprite) {
        this.renderer = renderer;

        weirdShape = new Sprite(weirdShapeSprite);
        weirdShape.setOriginCenter();
        weirdShape.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        weirdShape.setColor(Color.RED);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        renderer.useDynamicCamera();
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
        renderer.useStaticCamera();
    }
}