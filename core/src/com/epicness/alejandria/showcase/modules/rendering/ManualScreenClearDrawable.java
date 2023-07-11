package com.epicness.alejandria.showcase.modules.rendering;

import static com.badlogic.gdx.graphics.Color.ORANGE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.interfaces.Drawable;

public class ManualScreenClearDrawable implements Drawable {

    private final Sprited weirdShape;

    public ManualScreenClearDrawable(Sprite weirdShapeSprite) {
        weirdShape = new Sprited(weirdShapeSprite);
        weirdShape.setY(CAMERA_HALF_HEIGHT - weirdShape.getHeight() / 2f);
        weirdShape.setColor(ORANGE);
        weirdShape.useBilinearFilter();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        spriteBatch.begin();
        weirdShape.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
    }

    public Sprited getWeirdShape() {
        return weirdShape;
    }
}