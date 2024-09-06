package com.epicness.alejandria.showcase.modules.collisions;

import static com.badlogic.gdx.Input.Keys.SPACE;
import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_Y;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.Sprited;

public class PixelPerfectCollision extends Module<PixelPerfectCollisionDrawable> {

    private Pixmap pixmap;
    private Sprited[] shapes;

    public PixelPerfectCollision() {
        super(
            "Pixel Perfect Collision",
            "Clicking on a transparent pixel inside the regular bounding box will turn the shape red\n\n" +
                "Clicking on a non transparent pixel will turn it blue\n\n" +
                "Press space to rearrange shapes"
        );
    }

    @Override
    public PixelPerfectCollisionDrawable setup() {
        drawable = new PixelPerfectCollisionDrawable(sharedAssets.getWeirdShape());
        pixmap = drawable.getPixmap();
        shapes = drawable.getShapes();
        for (int i = 0; i < shapes.length; i++) {
            randomizePosition(shapes[i]);
        }
        return drawable;
    }

    @Override
    public void touchDown(float x, float y, int button) {
        for (int i = 0; i < shapes.length; i++) {
            Sprited shape = shapes[i];
            shape.setColor(WHITE);
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
                shape.setColor(RED);
            } else {
                shape.setColor(BLUE);
            }
        }
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode != SPACE) return;

        for (int i = 0; i < shapes.length; i++) {
            randomizePosition(shapes[i]);
        }
    }

    private void randomizePosition(Sprited shape) {
        float size = shape.getWidth();
        shape.setX(MathUtils.random(0f, VIEWPORT_WIDTH - size));
        shape.setY(MathUtils.random(SHOWCASE_Y, SHOWCASE_Y + SHOWCASE_SIZE - size));
    }
}