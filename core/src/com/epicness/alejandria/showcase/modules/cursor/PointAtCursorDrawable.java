package com.epicness.alejandria.showcase.modules.cursor;

import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;

public class PointAtCursorDrawable implements ModuleDrawable {

    private final Sprited triangle1, triangle2;

    public PointAtCursorDrawable(Sprite triangleSprite) {
        triangle1 = new Sprited(triangleSprite);
        triangle1.setOriginCenter();
        triangle1.setOriginBasedPosition(CAMERA_HALF_WIDTH + 200f, CAMERA_HALF_HEIGHT);

        triangle2 = new Sprited(triangleSprite);
        triangle2.setOriginCenter();
        triangle2.setOriginBasedPosition(CAMERA_HALF_WIDTH - 200f, CAMERA_HALF_HEIGHT);
        triangle2.useBilinearFilter();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        triangle1.draw(spriteBatch);
        triangle2.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        shapeRenderer.rect(triangle1.getBoundingRectangle());
        shapeRenderer.rect(triangle2.getBoundingRectangle());
    }

    public Sprited getTriangle1() {
        return triangle1;
    }

    public Sprited getTriangle2() {
        return triangle2;
    }
}