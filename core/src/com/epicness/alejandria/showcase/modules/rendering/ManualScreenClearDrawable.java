package com.epicness.alejandria.showcase.modules.rendering;

import static com.badlogic.gdx.graphics.Color.ORANGE;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;

public class ManualScreenClearDrawable implements ModuleDrawable {

    private final Sprited weirdShape;

    public ManualScreenClearDrawable(Sprite weirdShapeSprite) {
        weirdShape = new Sprited(weirdShapeSprite);
        weirdShape.setY(CAMERA_HALF_HEIGHT - weirdShape.getHeight() / 2f);
        weirdShape.setColor(ORANGE);
        weirdShape.useBilinearFilter();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public Sprited getWeirdShape() {
        return weirdShape;
    }
}