package com.epicness.alejandria.showcase.modules.rendering;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.interfaces.Drawable;

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
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        renderer.useDynamicCamera();
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
        renderer.useStaticCamera();
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
    }
}