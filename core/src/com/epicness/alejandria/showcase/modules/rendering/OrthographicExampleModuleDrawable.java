package com.epicness.alejandria.showcase.modules.rendering;

import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class OrthographicExampleModuleDrawable implements ModuleDrawable {

    private final Renderer<?> renderer;
    private final Sprite weirdShape;

    public OrthographicExampleModuleDrawable(Renderer<?> renderer, Sprite weirdShapeSprite) {
        this.renderer = renderer;

        weirdShape = new Sprite(weirdShapeSprite);
        weirdShape.setOriginCenter();
        weirdShape.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        weirdShape.setColor(Color.RED);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        renderer.useDynamicCamera();
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
        renderer.useStaticCamera();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }
}