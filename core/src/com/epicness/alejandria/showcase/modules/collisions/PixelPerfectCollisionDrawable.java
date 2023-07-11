package com.epicness.alejandria.showcase.modules.collisions;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.interfaces.Drawable;

public class PixelPerfectCollisionDrawable implements Drawable {

    private final Pixmap pixmap;
    private final Sprited[] shapes;

    public PixelPerfectCollisionDrawable(Sprite weirdShape) {
        TextureData textureData = weirdShape.getTexture().getTextureData();
        textureData.prepare();
        pixmap = textureData.consumePixmap();
        shapes = new Sprited[5];
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = new Sprited(weirdShape);
            shapes[i].setSize(MathUtils.random(175, 225));
        }
        shapes[0].useBilinearFilter();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        spriteBatch.begin();
        for (int i = 0; i < shapes.length; i++) {
            shapes[i].draw(spriteBatch);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
        for (int i = 0; i < shapes.length; i++) {
            shapes[i].drawDebug(shapeBatch);
        }
    }

    public Pixmap getPixmap() {
        return pixmap;
    }

    public Sprited[] getShapes() {
        return shapes;
    }
}