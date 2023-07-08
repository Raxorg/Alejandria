package com.epicness.alejandria.showcase.modules.collisions;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_Y;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

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
        shapes = new Sprited[10];
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = new Sprited(weirdShape);
            float size = MathUtils.random(150, 200);
            shapes[i].setSize(size, size);
            shapes[i].setX(MathUtils.random(0f, CAMERA_WIDTH - size));
            shapes[i].setY(MathUtils.random(SHOWCASE_Y, SHOWCASE_Y + SHOWCASE_SIZE - size));
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