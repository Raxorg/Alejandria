package com.epicness.alejandria.showcase.modules.cursor;

import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.SpritePlus;

public class PointAtCursorDrawable implements ModuleDrawable {

    private final SpritePlus triangle1, triangle2;

    public PointAtCursorDrawable(Sprite triangleSprite) {
        triangle1 = new SpritePlus(triangleSprite);
        triangle1.setOriginCenter();
        triangle1.setOriginBasedPosition(VIEWPORT_HALF_WIDTH + 200f, VIEWPORT_HALF_HEIGHT);

        triangle2 = new SpritePlus(triangleSprite);
        triangle2.setOriginCenter();
        triangle2.setOriginBasedPosition(VIEWPORT_HALF_WIDTH - 200f, VIEWPORT_HALF_HEIGHT);
        triangle2.useBilinearFilter();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        triangle1.draw(spriteBatch);
        triangle2.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.rectangle(triangle1.getBoundingRectangle());
        shapeDrawer.rectangle(triangle2.getBoundingRectangle());
    }

    public SpritePlus getTriangle1() {
        return triangle1;
    }

    public SpritePlus getTriangle2() {
        return triangle2;
    }
}