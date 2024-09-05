package com.epicness.alejandria.showcase.modules.rendering;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.alejandria.showcase.constants.RenderingConstants.COILS;
import static com.epicness.alejandria.showcase.constants.RenderingConstants.COLOR_STEPS;
import static com.epicness.alejandria.showcase.constants.RenderingConstants.X_STEP;
import static com.epicness.alejandria.showcase.constants.RenderingConstants.Y_STEP;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_Y;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;
import static com.epicness.fundamentals.utils.ColorUtils.HSVtoRGB;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class ShapeRenderingDrawable implements ModuleDrawable {

    private final Color color;
    private final Vector2 point1, point2, point3, point4, point5;

    public ShapeRenderingDrawable() {
        color = new Color();
        point1 = new Vector2();
        point2 = new Vector2();
        point3 = new Vector2();
        point4 = new Vector2();
        point5 = new Vector2();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        ScreenUtils.clear(BLACK);

        shapeRenderer.begin(Filled);
        for (float i = 0; i < COLOR_STEPS; i++) {
            Color.argb8888ToColor(color, HSVtoRGB(i / COLOR_STEPS, 1, 1));
            shapeRenderer.rect(
                VIEWPORT_HALF_WIDTH - 275,      // X
                VIEWPORT_HALF_HEIGHT - 275,     // Y
                275, 275,                       // Origin
                550, 550,                       // Size
                1, 1,                           // Scale
                i * 90 / COLOR_STEPS,           // Angle
                color, color, color, color);    // Colors
        }

        float xOffset, yOffset;
        for (int i = 0; i < COILS; i++) {
            xOffset = X_STEP * i;
            yOffset = Y_STEP * i;

            // Make this coil reach back to the outer coil
            point1.set(xOffset - X_STEP, yOffset + SHOWCASE_Y);
            point2.set(VIEWPORT_WIDTH - xOffset, yOffset + SHOWCASE_Y);
            point3.set(VIEWPORT_WIDTH - xOffset, SHOWCASE_SIZE - yOffset + SHOWCASE_Y);
            point4.set(xOffset, SHOWCASE_SIZE - yOffset + SHOWCASE_Y);
            // Make this coil stop before connecting back to itself
            point5.set(xOffset, yOffset + Y_STEP + SHOWCASE_Y);

            shapeRenderer.line(point1, point2);
            shapeRenderer.line(point2, point3);
            shapeRenderer.line(point3, point4);
            shapeRenderer.line(point4, point5);
        }
        shapeRenderer.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }
}