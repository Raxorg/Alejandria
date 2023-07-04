package com.epicness.alejandria.showcase.modules.collisions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.utils.Random;

public class PixelPerfectCollision extends Module<PixelPerfectCollisionDrawable> {

    public PixelPerfectCollision() {
        super(
                "Pixel Perfect Collision",
                "Click a shape to make it black, if you click on their transparent pixels they will turn into a random color"
        );
    }

    @Override
    public PixelPerfectCollisionDrawable setup() {
        return new PixelPerfectCollisionDrawable(sharedAssets.getWeirdShape());
    }

    public void touchDown(float x, float y) {
        Sprited[] shapes = drawable.getShapes();
        Pixmap pixmap = drawable.getPixmap();
        for (int i = 0; i < shapes.length; i++) {
            Sprited shape = shapes[i];
            if (!shape.getBoundingRectangle().contains(x, y)) {
                continue;
            }
            float inRangeEndX = shape.getX() + shape.getWidth();
            float inRangeEndY = shape.getY() + shape.getHeight();
            int pixmapX = (int) MathUtils.map(shape.getX(), inRangeEndX, 0, pixmap.getWidth(), x);
            int pixmapY = (int) MathUtils.map(shape.getY(), inRangeEndY, 0, pixmap.getHeight(), y);
            Color pixelColor = new Color();
            Color.rgba8888ToColor(pixelColor, pixmap.getPixel(pixmapX, pixmapY));
            if (pixelColor.a == 0) {
                shape.setColor(Random.opaqueColor());
            } else {
                shape.setColor(Color.BLACK);
            }
        }
    }
}