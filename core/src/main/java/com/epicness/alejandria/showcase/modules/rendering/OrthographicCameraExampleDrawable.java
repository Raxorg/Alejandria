package com.epicness.alejandria.showcase.modules.rendering;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class OrthographicCameraExampleDrawable implements ModuleDrawable {

    private final Renderer<?> renderer;
    private final Sprite weirdShape;

    public OrthographicCameraExampleDrawable(Renderer<?> renderer, Sprite weirdShapeSprite) {
        this.renderer = renderer;

        weirdShape = new Sprite(weirdShapeSprite);
        weirdShape.setOriginCenter();
        weirdShape.setOriginBasedPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
        weirdShape.setColor(RED);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        renderer.useDynamicCamera();
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
        renderer.useStaticCamera();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }
}