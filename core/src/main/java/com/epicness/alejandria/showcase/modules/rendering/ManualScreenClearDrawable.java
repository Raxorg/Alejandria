package com.epicness.alejandria.showcase.modules.rendering;

import static com.badlogic.gdx.graphics.Color.ORANGE;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.SpritePlus;

public class ManualScreenClearDrawable implements ModuleDrawable {

    private final SpritePlus weirdShape;

    public ManualScreenClearDrawable(Sprite weirdShapeSprite) {
        weirdShape = new SpritePlus(weirdShapeSprite);
        weirdShape.setY(VIEWPORT_HALF_HEIGHT - weirdShape.getHeight() * 0.5f);
        weirdShape.setColor(ORANGE);
        weirdShape.useBilinearFilter();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }

    public SpritePlus getWeirdShape() {
        return weirdShape;
    }
}