package com.epicness.alejandria.showcase.logic.modules.collisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.collisions.PixelPerfectCollisionDetectionDrawable;
import com.epicness.fundamentals.stuff.Sprited;

public class PixelPerfectCollisionDetection extends Module {

    private PixelPerfectCollisionDetectionDrawable drawable;

    @Override
    public void setup() {
        drawable = new PixelPerfectCollisionDetectionDrawable();
        stuff.getShowcase().setDrawable(drawable);
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.justTouched()) {
            float clickX = Gdx.input.getX();
            float clickY = Gdx.graphics.getHeight() - Gdx.input.getY();
            Sprited[] shapes = drawable.getShapes();
            Pixmap pixmap = drawable.getPixmap();
            for (int i = 0; i < shapes.length; i++) {
                Sprited shape = shapes[i];
                if (!shape.getBoundingRectangle().contains(clickX, clickY)) {
                    continue;
                }
                float inRangeEndX = shape.getX() + shape.getWidth();
                float inRangeEndY = shape.getY() + shape.getHeight();
                int pixmapX = (int) MathUtils.map(shape.getX(), inRangeEndX, 0, pixmap.getWidth(), clickX);
                int pixmapY = (int) MathUtils.map(shape.getY(), inRangeEndY, 0, pixmap.getHeight(), clickY);
                Color pixelColor = new Color();
                Color.rgba8888ToColor(pixelColor, pixmap.getPixel(pixmapX, pixmapY));
                if (pixelColor.a == 0) {
                    System.out.println("Transparent pixel clicked");
                }
                break;
            }
        }
    }
}