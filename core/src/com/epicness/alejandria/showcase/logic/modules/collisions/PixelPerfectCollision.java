package com.epicness.alejandria.showcase.logic.modules.collisions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.input.PixelPerfectCollisionInput;
import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.collisions.PixelPerfectCollisionDrawable;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.utils.Random;

public class PixelPerfectCollision extends Module {

    private PixelPerfectCollisionDrawable drawable;

    public PixelPerfectCollision() {
        super("Pixel Perfect Collision");
    }

    @Override
    public Drawable setup() {
        ShowcaseInputHandler inputHandler = (ShowcaseInputHandler) logic.getHandler(ShowcaseInputHandler.class);
        inputHandler.setModuleInputHandler(new PixelPerfectCollisionInput());
        return drawable = new PixelPerfectCollisionDrawable();
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
                shape.setColor(Random.fullyRandomColor());
            } else {
                shape.setColor(Color.BLACK);
            }
        }
    }
}